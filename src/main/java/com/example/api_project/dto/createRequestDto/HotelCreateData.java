package com.example.api_project.dto.createRequestDto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.List;

/**
 * Request DTO for hotel creation (POST /hotels).
 * Required: name, brand, address, contacts.
 * Optional: description, arrivalTime, amenities.
 */
public class HotelCreateData {
    @NotBlank(message = "Name is required")
    private String name;
    @NotBlank(message = "Brand is required")
    private String brand;
    @NotNull(message = "Address is required")
    @Valid
    private AddressCreateData address;
    @NotNull(message = "Contacts are required")
    @Valid
    private ContactCreateData contacts;

    private String description;
    private ArrivalTimeCreateData arrivalTime;
    private List<String> amenities;

    public HotelCreateData() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public AddressCreateData getAddress() {
        return address;
    }

    public void setAddress(AddressCreateData address) {
        this.address = address;
    }

    public ContactCreateData getContacts() {
        return contacts;
    }

    public void setContacts(ContactCreateData contacts) {
        this.contacts = contacts;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ArrivalTimeCreateData getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(ArrivalTimeCreateData arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public List<String> getAmenities() {
        return amenities;
    }

    public void setAmenities(List<String> amenities) {
        this.amenities = amenities;
    }
}



