package com.fpbinar6.code.services;

import java.util.List;

import com.fpbinar6.code.models.dto.TicketBookingResponseDTO;
import com.fpbinar6.code.models.dto.TicketRequestDTO;
import com.fpbinar6.code.models.dto.TicketResponseDTO;

public interface TicketService {
    public TicketResponseDTO getTicketById(Long id);
    public TicketBookingResponseDTO saveTicket(TicketRequestDTO ticketRequest);
    public List<TicketResponseDTO> getTicketByPaymentId(int id);
    public void deleteTicketById(Long id);
    public TicketBookingResponseDTO saveAllTickets(List<TicketRequestDTO> ticketRequests);
}
