package com.example.api_project.services;

import com.example.api_project.dto.HotelDetailedData;
import com.example.api_project.dto.HotelShortData;

import java.util.List;
import java.util.Optional;

public interface HotelService {
    List<HotelShortData> getAllHotels();
    HotelDetailedData getHotelById(Long id);
    List<HotelShortData> searchHotels(String name, String brand, String city, String country, String amenity);
}
