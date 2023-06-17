package com.fpbinar6.code.services;

import java.util.List;

import com.fpbinar6.code.models.dto.ScheduleRequestDTO;
import com.fpbinar6.code.models.dto.ScheduleResponseDTO;


public interface ScheduleService {
    List<ScheduleResponseDTO> getAllSchedule();
    ScheduleResponseDTO getScheduleById(int id);
    ScheduleResponseDTO saveSchedule(ScheduleRequestDTO ScheduleRequest);
}
