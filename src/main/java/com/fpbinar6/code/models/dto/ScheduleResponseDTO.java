package com.fpbinar6.code.models.dto;

import java.sql.Timestamp;

import com.fpbinar6.code.models.Airline;
import com.fpbinar6.code.models.Airport;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ScheduleResponseDTO {
    private int scheduleId;
    private Timestamp departureTime;
    private Timestamp arrivalTime;
    private Airport departureAirport;
    private Airport arrivalAirport;
    private Airline airline;
}
