package com.fpbinar6.code.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fpbinar6.code.models.Payment;
import com.fpbinar6.code.models.dto.PaymentAndTicketRequestDTO;
import com.fpbinar6.code.models.dto.PaymentResponseDTO;
import com.fpbinar6.code.models.dto.TicketResponseDTO;
import com.fpbinar6.code.repository.PaymentRepository;
import com.fpbinar6.code.services.PaymentService;
import com.fpbinar6.code.utils.Constants;
import com.fpbinar6.code.utils.ResponseHandler;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class PaymentController {

    final PaymentService paymentService;
    final PaymentRepository paymentRepository;

    @PostMapping("/payment/tickets")
    public ResponseEntity<List<TicketResponseDTO>> savePaymentAndTickets(
            @RequestBody PaymentAndTicketRequestDTO requestDTO) {
        List<TicketResponseDTO> savedTickets = paymentService.savePaymentAndTickets(requestDTO);
        return ResponseEntity.ok(savedTickets);
    }

    @PutMapping("/payment/pay/{paymentId}")
    public ResponseEntity<Object> markPaymentAsPaid(@PathVariable int paymentId) {
        Optional<Payment> paymentPay = paymentRepository.findById(paymentId);
        if (paymentPay.isPresent()) {
            Payment payment = paymentPay.get();
            payment.setPaymentStatus("paid");
            paymentRepository.save(payment);
            return ResponseHandler.generateResponse(Constants.SUCCESS_PAY_MSG, HttpStatus.OK,
                    payment);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{bookingId}")
    public ResponseEntity<PaymentResponseDTO> getPaymentByBookingId(@PathVariable String bookingId) {
        Payment payment = paymentRepository.findByBookingCode(bookingId);
        if (payment != null) {
            PaymentResponseDTO responseDTO = payment.convertToResponse();
            return ResponseEntity.ok(responseDTO);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
