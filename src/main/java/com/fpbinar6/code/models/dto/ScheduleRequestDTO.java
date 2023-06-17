package com.fpbinar6.code.models.dto;

import java.sql.Timestamp;

import com.fpbinar6.code.models.Airline;
import com.fpbinar6.code.models.Airport;
import com.fpbinar6.code.models.Schedule;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ScheduleRequestDTO {
    private Timestamp departureTime;
    private Timestamp arrivalTime;
    private int departureAirportId;
    private int arrivalAirportId;
    private int airlineId;

    public Schedule toSchedule(Airport departureAirport, Airport arrivalAirport, Airline airline){
        return Schedule.builder()
                .departureTime(this.departureTime)
                .arrivalTime(this.arrivalTime)
                .departureAirport(departureAirport)
                .arrivalAirport(arrivalAirport)
                .airline(airline)
                .build();
    }
}
