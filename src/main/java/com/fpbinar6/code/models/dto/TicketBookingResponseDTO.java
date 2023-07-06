package com.fpbinar6.code.models.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TicketBookingResponseDTO {
    private List<TicketResponseDTO> tickets;
    private String bookingCode;
    private int totalPrice;
    private String paymentStatus;
}
