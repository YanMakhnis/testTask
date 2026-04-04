package com.example.api_project.converters;

import com.example.api_project.dto.createRequestDto.ContactCreateData;
import com.example.api_project.dto.ContactData;
import com.example.api_project.entities.Contacts;

public interface ContactsConverter {
    ContactData convertToData(Contacts contacts);
    Contacts convertToEntity(ContactCreateData contactCreateData);

}
