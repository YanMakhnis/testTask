package com.example.api_project.converters;

import com.example.api_project.dto.createRequestDto.HotelCreateData;
import com.example.api_project.dto.HotelDetailedData;
import com.example.api_project.dto.HotelShortData;
import com.example.api_project.entities.Hotel;

public interface HotelConverter {
    HotelShortData convertToShortData(Hotel hotel);
    HotelDetailedData convertToDetailedData(Hotel hotel);
    Hotel convertToEntity(HotelCreateData hotelCreateData);
}
