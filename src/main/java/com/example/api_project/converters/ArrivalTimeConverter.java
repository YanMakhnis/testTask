package com.example.api_project.converters;

import com.example.api_project.dto.createRequestDto.ArrivalTimeCreateData;
import com.example.api_project.dto.ArrivalTimeData;
import com.example.api_project.entities.ArrivalTime;

public interface ArrivalTimeConverter {
    ArrivalTimeData convertToData(ArrivalTime arrivalTime);
    ArrivalTime convertToEntity(ArrivalTimeCreateData arrivalTimeCreateData);
}
