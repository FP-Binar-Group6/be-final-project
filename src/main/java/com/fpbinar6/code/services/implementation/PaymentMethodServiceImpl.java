package com.fpbinar6.code.services.implementation;

import com.fpbinar6.code.models.PaymentMethod;
import com.fpbinar6.code.repository.PaymentMethodRepository;
import com.fpbinar6.code.services.PaymentMethodService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PaymentMethodServiceImpl implements PaymentMethodService {
    final PaymentMethodRepository paymentMethodRepository;

    @Override
    public List<PaymentMethod> getAllPaymentMethod() {
        return paymentMethodRepository.findAll();
    }

    @Override
    public PaymentMethod getPaymentMethodById(int id) {
        return paymentMethodRepository.findById(id).orElseThrow();
    }

    @Override
    public PaymentMethod savePaymentMethod(PaymentMethod paymentMethod) {
        return paymentMethodRepository.save(paymentMethod);
    }

    @Override
    public PaymentMethod updatePaymentMethod(PaymentMethod updatePaymentMethod) {
        Optional<PaymentMethod> result = paymentMethodRepository.findById(updatePaymentMethod.getPaymentMethodId());
        PaymentMethod paymentMethod;
        if(result.isPresent()){
            paymentMethod = result.get();
            paymentMethod.setName(updatePaymentMethod.getName());
            return paymentMethodRepository.save(paymentMethod);
        }
        else {
            throw new RuntimeException("Data tidak ditemukan");
        }
    }

    @Override
    public void deletePaymentMethodById(int id) {
        Optional<PaymentMethod> result = paymentMethodRepository.findById(id);
        if(result.isEmpty()){
            throw new RuntimeException("Data tidak ditemukan");
        }
        paymentMethodRepository.delete(result.get());
    }
}
