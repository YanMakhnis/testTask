package com.example.api_project.converters;

import com.example.api_project.dto.createRequestDto.HotelCreateData;
import com.example.api_project.dto.HotelDetailedData;
import com.example.api_project.dto.HotelShortData;
import com.example.api_project.entities.Hotel;

/**
 * Converter interface for mapping between Hotel entity and various DTOs.
 * Provides bidirectional conversion for different use cases.
 */
public interface HotelConverter {
    HotelShortData convertToShortData(Hotel hotel);
    HotelDetailedData convertToDetailedData(Hotel hotel);
    Hotel convertToEntity(HotelCreateData hotelCreateData);
}
