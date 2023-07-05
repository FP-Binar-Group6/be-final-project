package com.fpbinar6.code.models.dto;

import com.fpbinar6.code.models.Schedule;
import com.fpbinar6.code.models.Seat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class SeatSeederDTO extends SeatRequestDTO {
    private int seatId;

    public Seat toSeat(Seat existingSeat, Schedule schedule) {
        existingSeat.setSeatNumber(existingSeat.getSeatNumber());
        existingSeat.setSchedule(schedule);
        existingSeat.setPicked(existingSeat.isPicked());
        return existingSeat;
    }
}
