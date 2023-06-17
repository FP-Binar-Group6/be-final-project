package com.fpbinar6.code.services;

import com.fpbinar6.code.models.PaymentMethod;

import java.util.List;

public interface PaymentMethodService {
    public List<PaymentMethod> getAllPaymentMethod();
    public PaymentMethod getPaymentMethodById(int id);
    public PaymentMethod savePaymentMethod(PaymentMethod paymentMethod);
    public PaymentMethod updatePaymentMethod(PaymentMethod paymentMethod);
    public void deletePaymentMethodById(int id);
}
