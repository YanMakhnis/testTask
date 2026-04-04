package com.example.api_project.converters.impl;

import com.example.api_project.converters.AddressConverter;
import com.example.api_project.dto.createRequestDto.AddressCreateData;
import com.example.api_project.dto.AddressData;
import com.example.api_project.entities.Address;
import org.springframework.stereotype.Component;

@Component
public class DefaultAddressConverter implements AddressConverter {
    @Override
    public AddressData convertToData(final Address address) {
        final AddressData data = new AddressData();
        data.setHouseNumber(address.getHouseNumber());
        data.setCity(address.getCity());
        data.setPostCode(address.getPostCode());
        data.setStreet(address.getStreet());
        data.setCountry(address.getCountry());
        return data;
    }

    @Override
    public Address convertToEntity(AddressCreateData addressCreateData) {
        final Address address = new Address();
        address.setCountry(addressCreateData.getCountry());
        address.setCity(addressCreateData.getCity());
        address.setStreet(addressCreateData.getStreet());
        address.setHouseNumber(addressCreateData.getHouseNumber());
        address.setPostCode(addressCreateData.getPostCode());
        return address;
    }
}
