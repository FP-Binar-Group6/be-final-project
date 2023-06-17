package com.fpbinar6.code.services;

import java.util.List;

import com.fpbinar6.code.models.dto.SeatRequestDTO;
import com.fpbinar6.code.models.dto.SeatResponseDTO;

public interface SeatService {
    List<SeatResponseDTO> getAllSeat();
    List<SeatResponseDTO> getSeatByScheduleId(int scheduleId);
    SeatResponseDTO getSeatById(int id);
    SeatResponseDTO saveSeat(SeatRequestDTO seatRequest);
}
