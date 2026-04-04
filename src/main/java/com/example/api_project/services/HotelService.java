package com.example.api_project.services;

import com.example.api_project.dto.createRequestDto.HotelCreateData;
import com.example.api_project.dto.HotelDetailedData;
import com.example.api_project.dto.HotelShortData;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public interface HotelService {
    List<HotelShortData> getAllHotels();
    HotelDetailedData getHotelById(Long id);
    List<HotelShortData> searchHotels(String name, String brand, String city, String country, String amenity);
    HotelShortData createHotel(HotelCreateData hotelCreateData);
    void addAmenities(Long hotelId, List<String> amenities);
    Map<String, Long> getHistogram(String param);
}
