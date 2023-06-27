package com.fpbinar6.code.services.implementation;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.fpbinar6.code.models.Ticket;
import com.fpbinar6.code.models.dto.TicketRequestDTO;
import com.fpbinar6.code.models.dto.TicketResponseDTO;
import com.fpbinar6.code.repository.SeatRepository;
import com.fpbinar6.code.repository.TicketRepository;
import com.fpbinar6.code.services.TicketService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TicketServiceImpl implements TicketService {

    final TicketRepository ticketRepository;
    final SeatRepository seatRepository;

    @Override
    public TicketResponseDTO getTicketById(int id) {
        Optional<Ticket> ticket = ticketRepository.findById(id);
        if (ticket.isPresent()) {
            return ticket.get().convertToResponse();
        }
        throw new RuntimeException("Data tidak ditemukan");
    }

    @Override
    public TicketResponseDTO saveTicket(TicketRequestDTO ticketRequest) {
        var seat = seatRepository.findById(ticketRequest.getSeatId()).orElseThrow(() -> new RuntimeException("Seat not found"));
        var ticket = ticketRequest.toTicket(seat);
        var result = ticketRepository.save(ticket);
        return result.convertToResponse();
    }

    @Override
    public void deleteTicketById(int id) {
        Optional<Ticket> result = ticketRepository.findById(id);
        if(result.isEmpty()){
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
