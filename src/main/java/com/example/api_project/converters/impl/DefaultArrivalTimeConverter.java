package com.example.api_project.converters.impl;

import com.example.api_project.converters.ArrivalTimeConverter;
import com.example.api_project.dto.createRequestDto.ArrivalTimeCreateData;
import com.example.api_project.dto.ArrivalTimeData;
import com.example.api_project.entities.ArrivalTime;
import org.springframework.stereotype.Component;

/**
 * Default implementation of {@link ArrivalTimeConverter}.
 */
@Component
public class DefaultArrivalTimeConverter implements ArrivalTimeConverter {
    @Override
    public ArrivalTimeData convertToData(final ArrivalTime arrivalTime) {
        final ArrivalTimeData data = new ArrivalTimeData();
        data.setCheckOut(arrivalTime.getCheckOut());
        data.setCheckIn(arrivalTime.getCheckIn());
        return data;
    }

    @Override
    public ArrivalTime convertToEntity(final ArrivalTimeCreateData arrivalTimeCreateData) {
        final ArrivalTime arrivalTime = new ArrivalTime();
        arrivalTime.setCheckIn(arrivalTimeCreateData.getCheckIn());
        arrivalTime.setCheckOut(arrivalTimeCreateData.getCheckOut());
        return arrivalTime;
    }
}
