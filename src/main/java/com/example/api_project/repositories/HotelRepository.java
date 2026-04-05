package com.example.api_project.repositories;

import com.example.api_project.dto.statisitcs.AmenityStatistic;
import com.example.api_project.dto.statisitcs.BrandStatistic;
import com.example.api_project.dto.statisitcs.CityStatistic;
import com.example.api_project.dto.statisitcs.CountryStatistic;
import com.example.api_project.entities.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository interface for {@link Hotel} entity.
 * Provides CRUD operations, dynamic specifications, and statistics queries.
 */
@Repository
public interface HotelRepository extends JpaRepository<Hotel, Long>, JpaSpecificationExecutor<Hotel> {

    /**
     * Checks if a hotel with the given name already exists.
     *
     * @param name hotel name to check
     * @return true if a hotel with this name exists, false otherwise
     */
    boolean existsByName(String name);

    /**
     * Counts hotels grouped by brand.
     *
     * @return list of {@link BrandStatistic} objects containing brand name and count
     */
    @Query("SELECT new com.example.api_project.dto.statisitcs.BrandStatistic(h.brand, COUNT(h)) " +
            "FROM Hotel h " +
            "WHERE h.brand IS NOT NULL " +
            "GROUP BY h.brand")
    List<BrandStatistic> countByBrand();

    /**
     * Counts hotels grouped by city.
     *
     * @return list of {@link CityStatistic} objects containing city name and count
     */
    @Query("SELECT new com.example.api_project.dto.statisitcs.CityStatistic(h.address.city, COUNT(h)) " +
            "FROM Hotel h " +
            "WHERE h.address.city IS NOT NULL " +
            "GROUP BY h.address.city")
    List<CityStatistic> countByCity();

    /**
     * Counts hotels grouped by country.
     *
     * @return list of {@link CountryStatistic} objects containing country name and count
     */
    @Query("SELECT new com.example.api_project.dto.statisitcs.CountryStatistic(h.address.country, COUNT(h)) " +
            "FROM Hotel h " +
            "WHERE h.address.country IS NOT NULL " +
            "GROUP BY h.address.country")
    List<CountryStatistic> countByCountry();

    /**
     * Counts hotels grouped by amenity.
     * Uses ELEMENT() to handle collection join properly.
     * Each hotel is counted once per amenity (DISTINCT).
     *
     * @return list of {@link AmenityStatistic} objects containing amenity name and count
     */
    @Query("SELECT new com.example.api_project.dto.statisitcs.AmenityStatistic(ELEMENT(a), COUNT(DISTINCT h)) " +
            "FROM Hotel h " +
            "JOIN h.amenities a " +
            "GROUP BY a")
    List<AmenityStatistic> countByAmenities();

}
