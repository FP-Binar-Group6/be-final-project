package com.fpbinar6.code.models.dto;

import java.util.List;

import com.fpbinar6.code.models.Payment;
import com.fpbinar6.code.models.PaymentMethod;
import com.fpbinar6.code.models.Ticket;
import com.fpbinar6.code.models.User;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PaymentRequestDTO {
    private List<Ticket> ticket;
    private int userId;
    private int paymentMethodId;

    public Payment toPayment(List<Ticket> ticket, User user, PaymentMethod paymentMethod) {
        return Payment.builder()
                .ticket(ticket)
                .user(user)
                .paymentMethod(paymentMethod)
                .build();
    }
}
