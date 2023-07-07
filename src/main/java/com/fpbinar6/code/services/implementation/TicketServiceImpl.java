package com.fpbinar6.code.services.implementation;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.fpbinar6.code.models.Payment;
import com.fpbinar6.code.models.Schedule;
import com.fpbinar6.code.models.Seat;
import com.fpbinar6.code.models.Ticket;
import com.fpbinar6.code.models.dto.TicketBookingResponseDTO;
import com.fpbinar6.code.models.dto.TicketRequestDTO;
import com.fpbinar6.code.models.dto.TicketResponseDTO;
import com.fpbinar6.code.repository.PaymentRepository;
import com.fpbinar6.code.repository.ScheduleRepository;
import com.fpbinar6.code.repository.SeatRepository;
import com.fpbinar6.code.repository.TicketRepository;
import com.fpbinar6.code.repository.UserRepository;
import com.fpbinar6.code.services.TicketService;
import com.fpbinar6.code.utils.GenerateRandomString;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TicketServiceImpl implements TicketService {

    final TicketRepository ticketRepository;
    final SeatRepository seatRepository;
    final ScheduleRepository scheduleRepository;
    final PaymentRepository paymentRepository;
    final UserRepository userRepository;

    @Override
    public TicketResponseDTO getTicketById(Long id) {
        Optional<Ticket> ticket = ticketRepository.findById(id);
        if (ticket.isPresent()) {
            return ticket.get().convertToResponse();
        }
        throw new RuntimeException("Data tidak ditemukan");
    }

    @Override
    public TicketBookingResponseDTO saveTicket(TicketRequestDTO ticketRequest) {
        String bookingCode = GenerateRandomString.generateRandomString(6);
        Payment payment = Payment.builder()
                .bookingCode(bookingCode)
                .user(userRepository.findById(ticketRequest.getUserId()).orElseThrow(() -> new RuntimeException("User not found")))
                .build();
        Payment savedPayment = paymentRepository.save(payment);
        if (ticketRequest.getSeatId() != null) {
            var seat = seatRepository.findById(ticketRequest.getSeatId())
                    .orElseThrow(() -> new RuntimeException("Seat not found"));
            var schedule = scheduleRepository.findById(ticketRequest.getScheduleId())
                    .orElseThrow(() -> new RuntimeException("Schedule not found"));
            var ticket = ticketRequest.toTicket(seat, schedule);
            ticket.setPayment(savedPayment);
            var savedTicket = ticketRepository.save(ticket);
            seat.setPicked(true);
            seatRepository.save(seat);

            int totalPrice = savedTicket.getSchedule().getKelas().getPrice();

            savedPayment.setTotalPrice(totalPrice);
            paymentRepository.save(savedPayment);

            return TicketBookingResponseDTO.builder()
                    .bookingCode(bookingCode)
                    .totalPrice(totalPrice)
                    .paymentStatus(savedPayment.getPaymentStatus())
                    .tickets(List.of(savedTicket.convertToResponse()))
                    .build();
        } else {
            var schedule = scheduleRepository.findById(ticketRequest.getScheduleId())
                    .orElseThrow(() -> new RuntimeException("Schedule not found"));
            var seat = seatRepository.findByScheduleId(ticketRequest.getScheduleId()).stream()
                    .filter(s -> !s.isPicked()).findFirst()
                    .orElseThrow(() -> new RuntimeException("Seat Full Booked"));
            var ticket = ticketRequest.toTicket(seat, schedule);
            ticket.setPayment(savedPayment);
            var savedTicket = ticketRepository.save(ticket);
            seat.setPicked(true);
            seatRepository.save(seat);

            int totalPrice = savedTicket.getSchedule().getKelas().getPrice();

            savedPayment.setTotalPrice(totalPrice);
            paymentRepository.save(savedPayment);

            return TicketBookingResponseDTO.builder()
                    .bookingCode(bookingCode)
                    .totalPrice(totalPrice)
                    .paymentStatus(savedPayment.getPaymentStatus())
                    .tickets(List.of(savedTicket.convertToResponse()))
                    .build();

            
        }
    }

    @Override
    public TicketBookingResponseDTO saveAllTickets(List<TicketRequestDTO> ticketRequests) {

        String bookingCode = GenerateRandomString.generateRandomString(6);
        Payment payment = Payment.builder()
                .bookingCode(bookingCode)
                .user(userRepository.findById(ticketRequests.get(0).getUserId()).orElseThrow(() -> new RuntimeException("User not found")))
                .build();
        Payment savedPayment = paymentRepository.save(payment);

        List<TicketResponseDTO> ticketResponses = new ArrayList<>();
        int totalPrice = 0;

        for (TicketRequestDTO ticketRequest : ticketRequests) {
            if (ticketRequest.getSeatId() != null) {
                Seat seat = seatRepository.findById(ticketRequest.getSeatId())
                        .orElseThrow(() -> new RuntimeException("Seat not found"));
                Schedule schedule = scheduleRepository.findById(ticketRequest.getScheduleId())
                        .orElseThrow(() -> new RuntimeException("Schedule not found"));
                Ticket ticket = ticketRequest.toTicket(seat, schedule);
                ticket.setPayment(savedPayment);
                Ticket savedTicket = ticketRepository.save(ticket);

                // Set isPicked to true for the associated seat
                seat.setPicked(true);
                seatRepository.save(seat);

                totalPrice += ticket.getSchedule().getKelas().getPrice();
                // Build the response DTO with ticketId and seatId
                TicketResponseDTO responseDTO = savedTicket.convertToResponse();

                ticketResponses.add(responseDTO);
            } else {
                Schedule schedule = scheduleRepository.findById(ticketRequest.getScheduleId())
                        .orElseThrow(() -> new RuntimeException("Schedule not found"));
                Seat seat = seatRepository.findByScheduleId(ticketRequest.getScheduleId()).stream()
                        .filter(s -> !s.isPicked())
                        .findFirst()
                        .orElseThrow(() -> new RuntimeException("Seat Full Booked"));
                Ticket ticket = ticketRequest.toTicket(seat, schedule);
                ticket.setPayment(savedPayment);
                Ticket savedTicket = ticketRepository.save(ticket);

                // Set isPicked to true for the associated seat
                seat.setPicked(true);
                seatRepository.save(seat);

                totalPrice += ticket.getSchedule().getKelas().getPrice();
                // Build the response DTO with ticketId and seatId
                TicketResponseDTO responseDTO = savedTicket.convertToResponse();

                ticketResponses.add(responseDTO);
            }
        }
        savedPayment.setTotalPrice(totalPrice);
        paymentRepository.save(savedPayment);

        TicketBookingResponseDTO bookingResponseDTO = TicketBookingResponseDTO.builder()
                .tickets(ticketResponses)
                .bookingCode(bookingCode)
                .paymentStatus(savedPayment.getPaymentStatus())
                .totalPrice(totalPrice)
                .build();

        return bookingResponseDTO;
    }

    @Override
    public void deleteTicketById(Long id) {
        Optional<Ticket> result = ticketRepository.findById(id);
        if (result.isEmpty()) {
            throw new RuntimeException("Data tidak ditemukan");
        }
        ticketRepository.delete(result.get());
    }

    @Override
    public List<TicketResponseDTO> getTicketByPaymentId(int id) {
        var tickets = ticketRepository.findByPaymentId(id);
        return tickets.stream().map(ticket -> {
            return ticket.convertToResponse();
        }).toList();
    }

}
