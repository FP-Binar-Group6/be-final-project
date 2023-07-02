package com.fpbinar6.code.models.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentAndTicketRequestDTO {
    private PaymentRequestDTO payment;
    private List<TicketRequestDTO> tickets;
}