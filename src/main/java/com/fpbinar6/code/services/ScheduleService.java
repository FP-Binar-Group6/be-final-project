package com.fpbinar6.code.services;

import java.util.List;

import com.fpbinar6.code.models.dto.ScheduleRequestDTO;
import com.fpbinar6.code.models.dto.ScheduleResponseDTO;


public interface ScheduleService {
    List<ScheduleResponseDTO> getAllSchedule();
    List<ScheduleResponseDTO> searchSchedules(ScheduleRequestDTO scheduleRequest);
    // List<ScheduleResponseDTO> searchSchedulesWithoutAirlineId(ScheduleRequestDTO scheduleRequest);
    ScheduleResponseDTO getScheduleById(int id);
    void deleteScheduleById(int id);
    ScheduleResponseDTO saveSchedule(ScheduleRequestDTO scheduleRequest);
}
