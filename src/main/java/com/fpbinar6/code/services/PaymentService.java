package com.fpbinar6.code.services;

import java.util.List;

import com.fpbinar6.code.models.Payment;
import com.fpbinar6.code.models.dto.PaymentAndTicketRequestDTO;
import com.fpbinar6.code.models.dto.TicketResponseDTO;

public interface PaymentService {
    
    public Payment getPaymentById(int id);
    public Payment savePayment(Payment payment);
    public void deletePaymentById(int id);
    public List<TicketResponseDTO> savePaymentAndTickets(PaymentAndTicketRequestDTO requestDTO);
}
