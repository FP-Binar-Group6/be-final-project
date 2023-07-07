package com.fpbinar6.code.models.dto;

import java.sql.Date;

import com.fpbinar6.code.models.Schedule;
import com.fpbinar6.code.models.Seat;
import com.fpbinar6.code.models.Ticket;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TicketRequestDTO {
    private String title;
    private String firstName;
    private String lastName;
    private Date birthDate;
    private String nationality;
    private String idCardNumber;
    private Integer seatId;
    private int scheduleId;
    private int userId;

    public Ticket toTicket(Seat seat, Schedule schedule){
        return Ticket.builder()
        .title(this.title)
        .firstName(this.firstName)
        .lastName(this.lastName)
        .birthDate(this.birthDate)
        .nationality(this.nationality)
        .idCardNumber(this.idCardNumber)
        .schedule(schedule)
        .seat(seat)
        .build();
    }

}
