package com.example.api_project.converters.impl;

import com.example.api_project.converters.ContactsConverter;
import com.example.api_project.dto.createRequestDto.ContactCreateData;
import com.example.api_project.dto.ContactData;
import com.example.api_project.entities.Contacts;
import org.springframework.stereotype.Component;

/**
 * Default implementation of {@link ContactsConverter}.
 */
@Component
public class DefaultContactsConverter implements ContactsConverter {
    @Override
    public ContactData convertToData(final Contacts contacts) {
        final ContactData data = new ContactData();
        data.setPhone(contacts.getPhone());
        data.setEmail(contacts.getEmail());
        return data;
    }

    @Override
    public Contacts convertToEntity(final ContactCreateData contactCreateData) {
        final Contacts contacts = new Contacts();
        contacts.setEmail(contactCreateData.getEmail());
        contacts.setPhone(contactCreateData.getPhone());
        return contacts;
    }
}
