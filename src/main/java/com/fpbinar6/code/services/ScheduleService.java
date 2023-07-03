package com.fpbinar6.code.services;

import java.sql.Timestamp;
import java.util.List;

import com.fpbinar6.code.models.dto.ScheduleRequestDTO;
import com.fpbinar6.code.models.dto.ScheduleResponseDTO;


public interface ScheduleService {
    List<ScheduleResponseDTO> getAllSchedule();
    List<ScheduleResponseDTO> searchSchedules(Timestamp departureTime, Integer departureAirportId, Integer arrivalAirportId, Integer airlineId, String className);
   // List<ScheduleResponseDTO> searchSchedulesWithAirlineId(ScheduleRequestDTO scheduleRequest);
    List<ScheduleResponseDTO> getRandomSchedules(int count);
    ScheduleResponseDTO getScheduleById(int id);
    void deleteScheduleById(int id);
    //ScheduleResponseDTO saveSchedule(ScheduleRequestDTO scheduleRequest);
}
