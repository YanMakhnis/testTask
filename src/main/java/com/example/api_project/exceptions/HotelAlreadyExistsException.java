package com.example.api_project.exceptions;

public class HotelAlreadyExistsException extends RuntimeException {
    public HotelAlreadyExistsException(final String message) {
        super(message);
    }
}
