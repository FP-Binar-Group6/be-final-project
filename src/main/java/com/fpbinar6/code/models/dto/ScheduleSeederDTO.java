package com.fpbinar6.code.models.dto;

import com.fpbinar6.code.models.Airline;
import com.fpbinar6.code.models.Airport;
import com.fpbinar6.code.models.Schedule;
import com.fpbinar6.code.models.Class;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ScheduleSeederDTO extends ScheduleRequestDTO {
    private int scheduleId;
    private Integer classId;

    public Schedule toSchedule(Class kelas, Schedule existingSchedule, Airport departureAirport, Airport arrivalAirport,
            Airline airline) {
        existingSchedule.setDepartureTime(existingSchedule.getDepartureTime());
        existingSchedule.setArrivalTime(existingSchedule.getArrivalTime());
        existingSchedule.setDepartureAirport(departureAirport);
        existingSchedule.setArrivalAirport(arrivalAirport);
        existingSchedule.setAirline(airline);
        existingSchedule.setKelas(kelas);
        return existingSchedule;
    }
}
