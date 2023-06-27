package com.fpbinar6.code.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.fpbinar6.code.models.Seat;

@Repository
public interface SeatRepository extends JpaRepository<Seat, Integer> {
    
    @Query(value = "SELECT * FROM public.seat s WHERE s.schedule_id = :id", nativeQuery = true)
    List<Seat> findByScheduleId(int id);
}
