package com.example.api_project.converters;

import com.example.api_project.dto.createRequestDto.AddressCreateData;
import com.example.api_project.dto.AddressData;
import com.example.api_project.entities.Address;

public interface AddressConverter {
    AddressData convertToData(Address address);
    Address convertToEntity(AddressCreateData addressCreateData);
}
