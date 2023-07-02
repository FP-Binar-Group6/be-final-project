package com.fpbinar6.code.models.dto;

import com.fpbinar6.code.models.Payment;
import com.fpbinar6.code.models.PaymentMethod;
import com.fpbinar6.code.models.User;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentRequestDTO {
    private int userId;
    private int paymentMethodId;

    public Payment toPayment() {
        User user = new User();
        user.setUserId(this.userId);

        PaymentMethod paymentMethod = new PaymentMethod();
        paymentMethod.setPaymentMethodId(this.paymentMethodId);

        return Payment.builder()
                .user(user)
                .paymentMethod(paymentMethod)
                .build();
    }
}

