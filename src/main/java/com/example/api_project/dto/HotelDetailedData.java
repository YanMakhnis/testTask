package com.example.api_project.dto;

import java.util.List;

/**
 * Detailed hotel information for list and search responses.
 */
public class HotelDetailedData {
    private Long id;
    private String name;
    private String description;
    private String brand;
    private AddressData address;
    private ContactData contacts;
    private ArrivalTimeData arrivalTime;
    private List<String> amenities;

    public HotelDetailedData() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public AddressData getAddress() {
        return address;
    }

    public void setAddress(AddressData address) {
        this.address = address;
    }

    public ContactData getContacts() {
        return contacts;
    }

    public void setContacts(ContactData contacts) {
        this.contacts = contacts;
    }

    public ArrivalTimeData getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(ArrivalTimeData arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public List<String> getAmenities() {
        return amenities;
    }

    public void setAmenities(List<String> amenities) {
        this.amenities = amenities;
    }
}
