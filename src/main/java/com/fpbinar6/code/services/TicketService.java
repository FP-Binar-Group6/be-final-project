package com.fpbinar6.code.services;

import com.fpbinar6.code.models.dto.TicketRequestDTO;
import com.fpbinar6.code.models.dto.TicketResponseDTO;

public interface TicketService {
    public TicketResponseDTO getTicketById(int id);
    public TicketResponseDTO saveTicket(TicketRequestDTO ticketRequest);
    public void deleteTicketById(int id);
}
