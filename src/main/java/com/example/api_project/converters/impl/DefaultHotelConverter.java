package com.example.api_project.converters.impl;

import com.example.api_project.converters.AddressConverter;
import com.example.api_project.converters.ArrivalTimeConverter;
import com.example.api_project.converters.ContactsConverter;
import com.example.api_project.converters.HotelConverter;
import com.example.api_project.dto.createRequestDto.HotelCreateData;
import com.example.api_project.dto.HotelDetailedData;
import com.example.api_project.dto.HotelShortData;
import com.example.api_project.entities.Hotel;
import com.example.api_project.utils.AddressUtil;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class DefaultHotelConverter implements HotelConverter {

    @Resource
    private AddressConverter addressConverter;
    @Resource
    private ContactsConverter contactsConverter;
    @Resource
    private ArrivalTimeConverter arrivalTimeConverter;

    @Override
    public HotelShortData convertToShortData(final Hotel hotel) {
        final HotelShortData data = new HotelShortData();
        data.setId(hotel.getId());
        data.setName(hotel.getName());
        data.setDescription(hotel.getDescription());
        data.setAddress(AddressUtil.resolveAddress(hotel.getAddress()));
        data.setPhone(Objects.nonNull(hotel.getContacts()) ? hotel.getContacts().getPhone() : null);
        return data;
    }

    @Override
    public HotelDetailedData convertToDetailedData(final Hotel hotel) {
        final HotelDetailedData data = new HotelDetailedData();
        data.setId(hotel.getId());
        data.setName(hotel.getName());
        data.setDescription(hotel.getDescription());
        data.setBrand(hotel.getBrand());
        data.setAddress(addressConverter.convertToData(hotel.getAddress()));
        data.setContacts(contactsConverter.convertToData(hotel.getContacts()));
        data.setArrivalTime(arrivalTimeConverter.convertToData(hotel.getArrivalTime()));
        data.setAmenities(hotel.getAmenities());
        return data;
    }

    @Override
    public Hotel convertToEntity(final HotelCreateData hotelCreateData) {
        final Hotel hotel = new Hotel();
        hotel.setName(hotelCreateData.getName());
        hotel.setDescription(hotelCreateData.getDescription());
        hotel.setBrand(hotelCreateData.getBrand());
        hotel.setAddress(addressConverter.convertToEntity(hotelCreateData.getAddress()));
        hotel.setContacts(contactsConverter.convertToEntity(hotelCreateData.getContacts()));
        hotel.setArrivalTime(arrivalTimeConverter.convertToEntity(hotelCreateData.getArrivalTime()));
        return hotel;
    }
}
