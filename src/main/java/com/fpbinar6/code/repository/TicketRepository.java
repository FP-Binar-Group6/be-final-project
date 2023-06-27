package com.fpbinar6.code.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.fpbinar6.code.models.Ticket;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Integer> {
    
    @Query(value = "SELECT * FROM public.ticket t WHERE t.payment_id = :id", nativeQuery = true)
    List<Ticket> findByPaymentId(int id);
}
