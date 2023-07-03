package com.fpbinar6.code.services.implementation;

import java.util.List;

import org.springframework.stereotype.Service;

import com.fpbinar6.code.models.dto.SeatRequestDTO;
import com.fpbinar6.code.models.dto.SeatResponseDTO;
import com.fpbinar6.code.repository.ClassRepository;
import com.fpbinar6.code.repository.ScheduleRepository;
import com.fpbinar6.code.repository.SeatRepository;
import com.fpbinar6.code.services.SeatService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SeatServiceImpl implements SeatService{
    
    final SeatRepository seatRepository;
    final ScheduleRepository scheduleRepository;
    final ClassRepository classRepository;
    
    @Override
    public List<SeatResponseDTO> getAllSeat() {
        var seats = seatRepository.findAll();
        return seats.stream().map(seat -> {
            return seat.convertToResponse();
        }).toList();
    }

    @Override
    public List<SeatResponseDTO> getSeatByScheduleId(int id) {
        var seats = seatRepository.findByScheduleId(id);
        return seats.stream().map(seat -> {
            return seat.convertToResponse();
        }).toList();
    }

    @Override
    public SeatResponseDTO getSeatById(int id) {
        var seat = seatRepository.findById(id);
        return seat.get().convertToResponse();
    }

    @Override
    public SeatResponseDTO saveSeat(SeatRequestDTO seatRequest) {
        var schedule = scheduleRepository.findById(seatRequest.getScheduleId()).orElseThrow(() -> new RuntimeException("Schedule not found"));
        var seat = seatRequest.toSeat(schedule);
        seat.setPicked(false);
        var result = seatRepository.save(seat);

        return result.convertToResponse();
    }

}
