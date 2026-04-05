package com.example.api_project.services;

import com.example.api_project.dto.createRequestDto.HotelCreateData;
import com.example.api_project.dto.HotelDetailedData;
import com.example.api_project.dto.HotelShortData;
import com.example.api_project.exceptions.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Service interface for hotel management operations.
 */
@Service
public interface HotelService {
    /**
     * Retrieves all hotels with short information.
     *
     * @return list of hotels with id, name, description, formatted address and phone
     */
    List<HotelShortData> getAllHotels();

    /**
     * Retrieves detailed information of a specific hotel by its ID.
     *
     * @param id hotel identifier
     * @return hotel details {@link HotelDetailedData},otherwise null
     */
    HotelDetailedData getHotelById(Long id);

    /**
     * Searches hotels by specified criteria.
     *
     * @param name    hotel name
     * @param brand   hotel brand
     * @param city    city name
     * @param country country name
     * @param amenity amenity name
     * @return list of hotels {@link HotelShortData} matching the criteria with short information
     */
    List<HotelShortData> searchHotels(String name, String brand, String city, String country, String amenity);

    /**
     * Creates a new hotel.
     *
     * @param hotelCreateData hotel data from request
     * @return created hotel with generated ID
     * @throws HotelAlreadyExistsException if hotel with same name already exists
     */
    HotelShortData createHotel(HotelCreateData hotelCreateData);

    /**
     * Adds amenities to an existing hotel.
     * Duplicate amenities are automatically ignored.
     *
     * @param hotelId   identifier of the hotel
     * @param amenities list of amenities to add
     * @throws HotelNotFoundException if hotel with given ID does not exist
     */
    void addAmenities(Long hotelId, List<String> amenities);

    /**
     * Returns statistics grouped by specified parameter.
     *
     * @param param grouping parameter
     * @return map where key is the parameter value and value is the count of hotels
     * @throws IllegalArgumentException if param is not one of allowed values
     */
    Map<String, Long> getHistogram(String param);
}
