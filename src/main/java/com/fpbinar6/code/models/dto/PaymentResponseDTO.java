package com.fpbinar6.code.models.dto;

import java.util.List;

import com.fpbinar6.code.models.PaymentMethod;
import com.fpbinar6.code.models.Ticket;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PaymentResponseDTO {
    private int paymentId;
    private String bookingCode;
    private List<Ticket> ticket;
    private int totalPrice;
    private String paymentStatus;
    private int userId;
    private PaymentMethod paymentMethod;

}
