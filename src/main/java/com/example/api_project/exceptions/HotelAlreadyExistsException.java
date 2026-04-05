package com.example.api_project.exceptions;

/**
 * Exception thrown when trying to create a hotel with an already existing name.
 */
public class HotelAlreadyExistsException extends RuntimeException {
    public HotelAlreadyExistsException(final String message) {
        super(message);
    }
}
