package com.fpbinar6.code.models.dto;

import com.fpbinar6.code.models.Schedule;
import com.fpbinar6.code.models.Seat;
import com.fpbinar6.code.models.Class;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SeatRequestDTO {
    private String seatNumber;
    private int scheduleId;
    private int classId;
    private boolean isPicked;

    public Seat toSeat(Class kelas, Schedule schedule){
        return Seat.builder()
                .kelas(kelas)
                .schedule(schedule)
                .seatNumber(this.seatNumber)
                .isPicked(this.isPicked)
                .build();
    }

}
