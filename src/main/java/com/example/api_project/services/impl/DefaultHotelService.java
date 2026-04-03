package com.example.api_project.services.impl;

import com.example.api_project.converters.HotelConverter;
import com.example.api_project.dto.HotelDetailedData;
import com.example.api_project.dto.HotelShortData;
import com.example.api_project.entities.Hotel;
import com.example.api_project.repositories.HotelRepository;
import com.example.api_project.services.HotelService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DefaultHotelService implements HotelService {
    @Resource
    private HotelRepository hotelRepository;
    @Resource
    private HotelConverter hotelConverter;

    @Override
    public List<HotelShortData> getAllHotels() {
        return hotelRepository.findAll().stream()
                .map(hotelConverter::convertToShortData)
                .toList();
    }

    @Override
    public HotelDetailedData getHotelById(Long id) {
        return hotelRepository.findById(id)
                .map(hotelConverter::convertToDetailedData)
                .orElse(null);
    }

    public List<HotelShortData> searchHotels(String name, String brand, String city, String country, String amenity) {
//        name = normalizeParam(name);
//        brand = normalizeParam(brand);
//        city = normalizeParam(city);
//        country = normalizeParam(country);
//        amenity = normalizeParam(amenity);

        List<Hotel> hotels = new ArrayList<>();

        return hotels.stream().map(hotelConverter::convertToShortData).toList();
    }
}
