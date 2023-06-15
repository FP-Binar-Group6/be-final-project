package com.fpbinar6.code.services.implementation;

import java.util.List;

import org.springframework.stereotype.Service;

import com.fpbinar6.code.models.Seat;
import com.fpbinar6.code.repository.SeatRepository;
import com.fpbinar6.code.services.SeatService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SeatServiceImpl implements SeatService{
    
    final SeatRepository seatRepository;
    
    @Override
    public List<Seat> getAllSeat() {
        return seatRepository.findAll();
    }

    @Override
    public List<Seat> getSeatByScheduleId(int scheduleId) {
        return seatRepository.findByScheduleId(scheduleId);
    }

    @Override
    public Seat getSeatById(int id) {
        return seatRepository.findById(id).get();
    }

    @Override
    public Seat saveSeat(Seat seat) {
        return seatRepository.save(seat);
    }

}
