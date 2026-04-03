package com.example.api_project.controllers;

import com.example.api_project.dto.HotelDetailedData;
import com.example.api_project.dto.HotelShortData;
import com.example.api_project.entities.Hotel;
import com.example.api_project.repositories.HotelRepository;
import com.example.api_project.services.HotelService;
import jakarta.annotation.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class HotelController {
    @Resource
    private HotelRepository hotelRepository;
    @Resource
    private HotelService hotelService;

    //TODO check the possibility to use  common dto
    @GetMapping
    public List<HotelShortData> getAllHotels() {
        return hotelService.getAllHotels();
    }

    @GetMapping("/{id}")
    public ResponseEntity<HotelDetailedData> getHotelById(@PathVariable Long id) {
        HotelDetailedData hotel = hotelService.getHotelById(id);
        return hotel != null ? ResponseEntity.ok(hotel) : ResponseEntity.notFound().build();
    }

    @GetMapping("/search")
    public List<HotelShortData> searchHotels(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String brand,
            @RequestParam(required = false) String city,
            @RequestParam(required = false) String country,
            @RequestParam(required = false) String amenities) {

        return hotelService.searchHotels(name, brand, city, country, amenities);
    }
}
