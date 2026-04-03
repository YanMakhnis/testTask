package com.example.api_project.converters.impl;

import com.example.api_project.converters.HotelConverter;
import com.example.api_project.dto.HotelDetailedData;
import com.example.api_project.dto.HotelShortData;
import com.example.api_project.entities.Hotel;
import com.example.api_project.utils.AddressUtil;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class DefaultHotelConverter implements HotelConverter {

    @Override
    public HotelShortData convertToShortData(Hotel hotel) {
        HotelShortData data = new HotelShortData();
        data.setId(hotel.getId());
        data.setName(hotel.getName());
        data.setDescription(hotel.getDescription());
        data.setAddress(AddressUtil.resolveAddress(hotel.getAddress()));
        data.setPhone(Objects.nonNull(hotel.getContacts()) ? hotel.getContacts().getPhone() : null);
        return data;
    }

    @Override
    public HotelDetailedData convertToDetailedData(Hotel hotel) {
        HotelDetailedData data = new HotelDetailedData();
        data.setId(hotel.getId());
        data.setName(hotel.getName());
        data.setDescription(hotel.getDescription());
        data.setBrand(hotel.getBrand());
        data.setAddress(hotel.getAddress());
        data.setContacts(hotel.getContacts());
        data.setArrivalTime(hotel.getArrivalTime());
        data.setAmenities(hotel.getAmenities());
        return data;
    }
}
