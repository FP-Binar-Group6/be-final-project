package com.fpbinar6.code.models.dto;

import com.fpbinar6.code.models.Airline;
import com.fpbinar6.code.models.Airport;
import com.fpbinar6.code.models.Schedule;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ScheduleSeederDTO extends ScheduleRequestDTO {
    private int scheduleId;

    public Schedule toSchedule(Schedule existingSchedule, Airport departureAirport, Airport arrivalAirport,
            Airline airline) {
        existingSchedule.setDepartureTime(this.departureTime);
        existingSchedule.setArrivalTime(this.arrivalTime);
        existingSchedule.setDepartureAirport(departureAirport);
        existingSchedule.setArrivalAirport(arrivalAirport);
        existingSchedule.setAirline(airline);
        return existingSchedule;
    }
}
