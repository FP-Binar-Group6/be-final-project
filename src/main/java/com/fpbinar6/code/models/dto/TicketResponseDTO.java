package com.fpbinar6.code.models.dto;

import java.sql.Date;

import com.fpbinar6.code.models.Seat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TicketResponseDTO {
    private Long ticketId;
    private String title;
    private String firstName;
    private String lastName;
    private Date birthDate;
    private String nationality;
    private String idCardNumber;
    private Seat seat;
}
