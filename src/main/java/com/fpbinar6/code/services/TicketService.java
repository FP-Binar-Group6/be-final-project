package com.fpbinar6.code.services;

import java.util.List;

import com.fpbinar6.code.models.dto.TicketRequestDTO;
import com.fpbinar6.code.models.dto.TicketResponseDTO;

public interface TicketService {
    public TicketResponseDTO getTicketById(Long id);
    public TicketResponseDTO saveTicket(TicketRequestDTO ticketRequest);
    public List<TicketResponseDTO> getTicketByPaymentId(int id);
    public void deleteTicketById(Long id);
    public List<TicketResponseDTO> saveAllTickets(List<TicketRequestDTO> ticketRequests);
}
