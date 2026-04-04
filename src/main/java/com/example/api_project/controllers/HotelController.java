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

@RestController
@RequestMapping("/property-view")
public class HotelController {
    @Resource
    private HotelService hotelService;

    @GetMapping("/hotels")
    public List<HotelShortData> getAllHotels() {
        return hotelService.getAllHotels();
    }

    @GetMapping("/hotels/{id}")
    public ResponseEntity<HotelDetailedData> getHotelById(@PathVariable Long id) {
        HotelDetailedData hotel = hotelService.getHotelById(id);
        return hotel != null ? ResponseEntity.ok(hotel) : ResponseEntity.notFound().build();
    }

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

    @PostMapping("/hotels")
    public ResponseEntity<HotelShortData> createHotel(@Valid @RequestBody final HotelCreateData hotelCreateData) {
        final HotelShortData createdHotel = hotelService.createHotel(hotelCreateData);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdHotel);
    }

    @PostMapping("/hotels/{id}/amenities")
    public ResponseEntity<HotelDetailedData> addAmenities(
            @PathVariable final Long id,
            @RequestBody final List<String> amenities) {
        hotelService.addAmenities(id, amenities);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/histogram/{param}")
    public ResponseEntity<Map<String, Long>> getHistogram(@PathVariable final String param) {
        Map<String, Long> histogram = hotelService.getHistogram(param);
        return ResponseEntity.ok(histogram);
    }
}
