package com.example.api_project.converters;

import com.example.api_project.dto.createRequestDto.AddressCreateData;
import com.example.api_project.dto.AddressData;
import com.example.api_project.entities.Address;

/**
 * Converter interface for mapping between Address entity and various DTOs.
 * Provides bidirectional conversion for different use cases.
 */
public interface AddressConverter {
    AddressData convertToData(Address address);
    Address convertToEntity(AddressCreateData addressCreateData);
}
