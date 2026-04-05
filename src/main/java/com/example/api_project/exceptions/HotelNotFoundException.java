package com.example.api_project.exceptions;

/**
 * Exception thrown when a hotel is not found by ID.
 */
public class HotelNotFoundException extends RuntimeException {
    public HotelNotFoundException(final String message) {
        super(message);
    }
}