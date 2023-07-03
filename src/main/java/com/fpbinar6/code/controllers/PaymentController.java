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
import com.fpbinar6.code.models.PaymentMethod;
import com.fpbinar6.code.models.User;
import com.fpbinar6.code.models.dto.PaymentAndTicketRequestDTO;
import com.fpbinar6.code.models.dto.PaymentRequestDTO;
import com.fpbinar6.code.models.dto.PaymentResponseDTO;
import com.fpbinar6.code.models.dto.TicketResponseDTO;
import com.fpbinar6.code.repository.PaymentMethodRepository;
import com.fpbinar6.code.repository.PaymentRepository;
import com.fpbinar6.code.repository.UserRepository;
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
    final UserRepository userRepository;
    final PaymentMethodRepository paymentMethodRepository;

    @PostMapping("/payment/tickets")
    public ResponseEntity<List<TicketResponseDTO>> savePaymentAndTickets(
            @RequestBody PaymentAndTicketRequestDTO requestDTO) {
        List<TicketResponseDTO> savedTickets = paymentService.savePaymentAndTickets(requestDTO);
        return ResponseEntity.ok(savedTickets);
    }

    @PutMapping("/payment/book/{paymentId}")
    public ResponseEntity<Object> updatePaymentData(
            @PathVariable("paymentId") Integer paymentId,
            @RequestBody PaymentRequestDTO paymentRequestDTO
    ) {
        Payment payment = paymentRepository.findById(paymentId)
                .orElseThrow(() -> new RuntimeException("Payment not found"));

        User user = userRepository.findById(paymentRequestDTO.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        PaymentMethod paymentMethod = paymentMethodRepository.findById(paymentRequestDTO.getPaymentMethodId())
                .orElseThrow(() -> new RuntimeException("Payment method not found"));

        // Update payment data
        payment.setUser(user);
        payment.setPaymentMethod(paymentMethod);

        // Save the updated payment
        paymentRepository.save(payment);

        return ResponseHandler.generateResponse(Constants.SUCCESS_EDIT_MSG, HttpStatus.OK, payment);
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
