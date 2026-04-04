package com.example.api_project.exceptions;

public class HotelNotFoundException extends RuntimeException {
    public HotelNotFoundException(final String message) {
        super(message);
    }
}