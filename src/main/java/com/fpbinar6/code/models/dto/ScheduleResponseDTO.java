package com.fpbinar6.code.models.dto;

import java.sql.Timestamp;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fpbinar6.code.models.Airline;
import com.fpbinar6.code.models.Airport;
import com.fpbinar6.code.models.Class;

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
    
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Timestamp departureTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Timestamp arrivalTime;
    private Airport departureAirport;
    private Airport arrivalAirport;
    private Airline airline;
    private Class kelas;
}
