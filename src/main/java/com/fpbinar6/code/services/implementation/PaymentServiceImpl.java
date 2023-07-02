package com.fpbinar6.code.services.implementation;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.fpbinar6.code.models.Payment;
import com.fpbinar6.code.models.Schedule;
import com.fpbinar6.code.models.Seat;
import com.fpbinar6.code.models.Ticket;
import com.fpbinar6.code.models.dto.PaymentAndTicketRequestDTO;
import com.fpbinar6.code.models.dto.TicketRequestDTO;
import com.fpbinar6.code.models.dto.TicketResponseDTO;
import com.fpbinar6.code.repository.PaymentRepository;
import com.fpbinar6.code.repository.ScheduleRepository;
import com.fpbinar6.code.repository.SeatRepository;
import com.fpbinar6.code.repository.TicketRepository;
import com.fpbinar6.code.services.PaymentService;
import com.fpbinar6.code.utils.GenerateRandomString;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {

    final PaymentRepository paymentRepository;
    final SeatRepository seatRepository;
    final ScheduleRepository scheduleRepository;
    final TicketRepository ticketRepository;

    @Override
    public Payment getPaymentById(int id) {
        throw new UnsupportedOperationException("Unimplemented method 'getPaymentById'");
    }

    @Override
    public Payment savePayment(Payment payment) {
        throw new UnsupportedOperationException("Unimplemented method 'savePayment'");
    }

    @Override
    public List<TicketResponseDTO> savePaymentAndTickets(PaymentAndTicketRequestDTO requestDTO) {
        Payment payment = requestDTO.getPayment().toPayment();
        List<TicketRequestDTO> ticketRequests = requestDTO.getTickets();

        payment.setBookingCode(GenerateRandomString.generateRandomString(6));

        Payment savedPayment = paymentRepository.save(payment);
        List<TicketResponseDTO> savedTickets = new ArrayList<>();

        for (TicketRequestDTO ticketRequest : ticketRequests) {
            if (ticketRequest.getSeatId() != null) {
                Seat seat = seatRepository.findById(ticketRequest.getSeatId())
                        .orElseThrow(() -> new RuntimeException("Seat not found"));
                Schedule schedule = scheduleRepository.findById(ticketRequest.getScheduleId())
                        .orElseThrow(() -> new RuntimeException("Schedule not found"));
                Ticket ticket = ticketRequest.toTicket(seat, schedule);
                ticket.setPayment(savedPayment);
                seat.setPicked(true);
                seatRepository.save(seat);
                Ticket savedTicket = ticketRepository.save(ticket);
                savedTickets.add(savedTicket.convertToResponse());
            } else {
                Schedule schedule = scheduleRepository.findById(ticketRequest.getScheduleId())
                        .orElseThrow(() -> new RuntimeException("Schedule not found"));
                Seat seat = seatRepository.findByScheduleId(ticketRequest.getScheduleId()).stream()
                        .filter(s -> s.isPicked() == false).findFirst()
                        .orElseThrow(() -> new RuntimeException("Seat not found"));
                Ticket ticket = ticketRequest.toTicket(seat, schedule);
                ticket.setPayment(savedPayment);
                seat.setPicked(true);
                seatRepository.save(seat);
                Ticket savedTicket = ticketRepository.save(ticket);
                savedTickets.add(savedTicket.convertToResponse());
            }
        }

        return savedTickets;
    }

    @Override
    public void deletePaymentById(int id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deletePaymentById'");
    }

}
