package com.fpbinar6.code.services;

import java.util.List;

import com.fpbinar6.code.models.Seat;

public interface SeatService {
    List<Seat> getAllSeat();
    List<Seat> getSeatByScheduleId(int scheduleId);
    Seat getSeatById(int id);
    Seat saveSeat(Seat seat);
}
