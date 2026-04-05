package com.example.api_project.controllers;

import com.example.api_project.dto.createRequestDto.HotelCreateData;
import com.example.api_project.dto.HotelDetailedData;
import com.example.api_project.dto.HotelShortData;
import com.example.api_project.services.HotelService;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * Rest controller containing api for hotel management.
 */
@RestController
@RequestMapping("/property-view")
public class HotelController {
    @Resource
    private HotelService hotelService;

    /**
     * Get all hotels with short information.
     *
     * @return list of {@link HotelShortData} containing hotel
     */
    @GetMapping("/hotels")
    public List<HotelShortData> getAllHotels() {
        return hotelService.getAllHotels();
    }

    /**
     * Get detailed hotel information by ID.
     *
     * @param id hotel identifier
     * @return hotel details {@link HotelDetailedData} with 200 OK, otherwise 404 Not Found if hotel doesn't exist
     */
    @GetMapping("/hotels/{id}")
    public ResponseEntity<HotelDetailedData> getHotelById(@PathVariable Long id) {
        HotelDetailedData hotel = hotelService.getHotelById(id);
        return hotel != null ? ResponseEntity.ok(hotel) : ResponseEntity.notFound().build();
    }

    /**
     * Searches hotels by specified criteria.
     * All parameters are optional. Returns list of {@link HotelShortData}.
     *
     * @param name      hotel name
     * @param brand     hotel brand
     * @param city      city name
     * @param country   country name
     * @param amenities amenity name
     * @return list of hotels list of {@link HotelShortData} if matching found
     */
    @GetMapping("/search")
    public ResponseEntity<List<HotelShortData>> searchHotels(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String brand,
            @RequestParam(required = false) String city,
            @RequestParam(required = false) String country,
            @RequestParam(required = false) String amenities) {
        List<HotelShortData> hotelsPerParameters = hotelService.searchHotels(name, brand, city, country, amenities);
        return CollectionUtils.isNotEmpty(hotelsPerParameters)
                ? ResponseEntity.ok(hotelsPerParameters)
                : ResponseEntity.notFound().build();
    }

    /**
     * Creates a new hotel.
     *
     * @param hotelCreateData {@link HotelCreateData} data from request body
     * @return created hotel {@link HotelShortData} with generated ID and HTTP 201 Created status
     */
    @PostMapping("/hotels")
    public ResponseEntity<HotelShortData> createHotel(@Valid @RequestBody final HotelCreateData hotelCreateData) {
        final HotelShortData createdHotel = hotelService.createHotel(hotelCreateData);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdHotel);
    }

    /**
     * Adds amenities to an existing hotel.
     * Duplicate amenities are ignored. Returns only HTTP status without body.
     *
     * @param id        hotel identifier
     * @param amenities list of amenities to add
     * @return HTTP 204 No Content on success, otherwise HTTP 404 Not Found if hotel doesn't exist
     */
    @PostMapping("/hotels/{id}/amenities")
    public ResponseEntity<HotelDetailedData> addAmenities(
            @PathVariable final Long id,
            @RequestBody final List<String> amenities) {
        hotelService.addAmenities(id, amenities);
        return ResponseEntity.noContent().build();
    }

    /**
     * Returns statistics grouped by specified parameter.
     *
     * @param param grouping parameter
     * @return map where key is the parameter value and value is the count of hotels
     * @throws IllegalArgumentException if param is not one of allowed values
     */
    @GetMapping("/histogram/{param}")
    public ResponseEntity<Map<String, Long>> getHistogram(@PathVariable final String param) {
        Map<String, Long> histogram = hotelService.getHistogram(param);
        return ResponseEntity.ok(histogram);
    }
}
