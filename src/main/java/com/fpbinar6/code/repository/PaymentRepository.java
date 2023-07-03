package com.fpbinar6.code.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.fpbinar6.code.models.Payment;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Integer> {
    
    @Query(value = "SELECT * FROM payment WHERE booking_code = ?1", nativeQuery = true)
    public Optional<Payment> findByBookingCode(String bookingCode);

    List<Payment> findByUser_UserId(int userId);
}
