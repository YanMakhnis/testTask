package com.example.api_project.specifications;

import com.example.api_project.entities.Hotel;
import jakarta.persistence.criteria.Join;
import org.springframework.data.jpa.domain.Specification;

/**
 * JPA Specifications for dynamic hotel search.
 * All methods are null-safe (returns null for null input).
 */
public class HotelSpecifications {

    /**
     * Filter by name (partial match, case-insensitive).
     *
     * @param name hotel name (partial match)
     * @return specification or null if name is null
     */
    public static Specification<Hotel> hasName(String name) {
        return (root, query, cb) ->
                name == null ? null :
                        cb.like(cb.lower(root.get("name")), "%" + name.toLowerCase() + "%");
    }

    /**
     * Filter by brand (exact match, case-insensitive).
     *
     * @param brand hotel brand
     * @return specification or null if brand is null
     */
    public static Specification<Hotel> hasBrand(String brand) {
        return (root, query, cb) ->
                brand == null ? null :
                        cb.equal(cb.lower(root.get("brand")), brand.toLowerCase());
    }

    /**
     * Filter by city (exact match, case-insensitive).
     *
     * @param city city name
     * @return specification or null if city is null
     */
    public static Specification<Hotel> hasCity(String city) {
        return (root, query, cb) ->
                city == null ? null :
                        cb.equal(cb.lower(root.get("address").get("city")), city.toLowerCase());
    }

    /**
     * Filter by country (exact match, case-insensitive).
     *
     * @param country country name
     * @return specification or null if country is null
     */
    public static Specification<Hotel> hasCountry(String country) {
        return (root, query, cb) ->
                country == null ? null :
                        cb.equal(cb.lower(root.get("address").get("country")), country.toLowerCase());
    }

    /**
     * Filter by amenity (exact match, case-insensitive).
     * Performs JOIN with amenities collection.
     *
     * @param amenity amenity name
     * @return specification or null if amenity is null
     */
    public static Specification<Hotel> hasAmenity(String amenity) {
        return (root, query, cb) -> {
            if (amenity == null) return null;

            Join<Hotel, String> amenitiesJoin = root.join("amenities");
            return cb.equal(cb.lower(amenitiesJoin), amenity.toLowerCase());
        };
    }
}
