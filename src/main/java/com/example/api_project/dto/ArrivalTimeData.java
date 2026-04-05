package com.example.api_project.dto;

/**
 * Arrival time information DTO.
 */
public class ArrivalTimeData {
    private String checkIn;
    private String checkOut;

    public String getCheckIn() {
        return checkIn;
    }

    public void setCheckIn(String checkIn) {
        this.checkIn = checkIn;
    }

    public String getCheckOut() {
        return checkOut;
    }

    public void setCheckOut(String checkOut) {
        this.checkOut = checkOut;
    }
}
