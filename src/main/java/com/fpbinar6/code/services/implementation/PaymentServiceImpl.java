package com.fpbinar6.code.services.implementation;

import org.springframework.stereotype.Service;

import com.fpbinar6.code.models.Payment;
import com.fpbinar6.code.repository.PaymentRepository;
import com.fpbinar6.code.services.PaymentService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {

    final PaymentRepository paymentRepository;

    @Override
    public Payment getPaymentById(int id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getPaymentById'");
    }

    @Override
    public Payment savePayment(Payment payment) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'savePayment'");
    }

    @Override
    public void deletePaymentById(int id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deletePaymentById'");
    }
    
}
