package com.fpbinar6.code.services;

import com.fpbinar6.code.models.Payment;

public interface PaymentService {
    
    public Payment getPaymentById(int id);
    public Payment savePayment(Payment payment);
    public void deletePaymentById(int id);
}
