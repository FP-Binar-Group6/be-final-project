package com.fpbinar6.code.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fpbinar6.code.models.Ticket;

public interface TicketRepository extends JpaRepository<Ticket, Integer> {
    
}
