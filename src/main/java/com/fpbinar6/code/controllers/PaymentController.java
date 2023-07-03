package com.fpbinar6.code.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fpbinar6.code.models.Payment;
import com.fpbinar6.code.models.PaymentMethod;
import com.fpbinar6.code.models.Ticket;
import com.fpbinar6.code.models.User;
import com.fpbinar6.code.models.dto.HistoryResponseDTO;
import com.fpbinar6.code.models.dto.PaymentAndTicketRequestDTO;
import com.fpbinar6.code.models.dto.PaymentRequestDTO;
import com.fpbinar6.code.models.dto.PaymentResponseDTO;
import com.fpbinar6.code.models.dto.TicketResponseDTO;
import com.fpbinar6.code.repository.PaymentMethodRepository;
import com.fpbinar6.code.repository.PaymentRepository;
import com.fpbinar6.code.repository.TicketRepository;
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
    final TicketRepository ticketRepository;

    @PostMapping("/payment/tickets")
    public ResponseEntity<List<TicketResponseDTO>> savePaymentAndTickets(
            @RequestBody PaymentAndTicketRequestDTO requestDTO) {
        List<TicketResponseDTO> savedTickets = paymentService.savePaymentAndTickets(requestDTO);
        return ResponseEntity.ok(savedTickets);
    }

    @PutMapping("/payment/book/{paymentId}")
    public ResponseEntity<Object> updatePaymentData(
            @PathVariable("paymentId") Integer paymentId,
            @RequestBody PaymentRequestDTO paymentRequestDTO) {
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

        return ResponseHandler.generateResponse(Constants.SUCCESS_EDIT_MSG, HttpStatus.OK, payment.getBookingCode());
    }

    @PutMapping("/payment/pay/{paymentId}")
    public ResponseEntity<Object> markPaymentAsPaid(@PathVariable int paymentId) {
        Optional<Payment> paymentPay = paymentRepository.findById(paymentId);
        if (paymentPay.isPresent()) {
            Payment payment = paymentPay.get();
            payment.setPaymentStatus("paid");
            paymentRepository.save(payment);

            var message = "Payment with booking code " + payment.getBookingCode() + " has been paid";
            return ResponseHandler.generateResponse(Constants.SUCCESS_PAY_MSG, HttpStatus.OK,
                    message);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/payment/history/{userId}")
    public ResponseEntity<Object> getUserHistory(@PathVariable int userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        List<Payment> payments = paymentRepository.findByUser_UserId(userId);

        List<HistoryResponseDTO> historyEntries = new ArrayList<>();

        for (Payment payment : payments) {
            List<Ticket> tickets = ticketRepository.findByPayment_PaymentId(payment.getPaymentId());

            List<TicketResponseDTO> ticketResponses = tickets.stream()
                    .map(Ticket::convertToResponse)
                    .collect(Collectors.toList());

            HistoryResponseDTO historyEntry = new HistoryResponseDTO();
            historyEntry.setPayment(payment.convertToResponse());
            historyEntry.setTickets(ticketResponses);

            historyEntries.add(historyEntry);
        }

        return ResponseEntity.ok(historyEntries);
    }

    @GetMapping("/payment/search/{bookingCode}")
    public ResponseEntity<Object> searchPaymentByBookingCode(@PathVariable("bookingCode") String bookingCode) {
        Payment payment = paymentRepository.findByBookingCode(bookingCode)
                .orElseThrow(() -> new RuntimeException("Payment not found"));

        List<Ticket> tickets = ticketRepository.findByPayment_PaymentId(payment.getPaymentId());

        List<TicketResponseDTO> ticketResponses = tickets.stream()
                .map(Ticket::convertToResponse)
                .collect(Collectors.toList());

        HistoryResponseDTO historyResponse = new HistoryResponseDTO();
        historyResponse.setPayment(payment.convertToResponse());
        historyResponse.setTickets(ticketResponses);

        return ResponseEntity.ok(historyResponse);
    }

}
