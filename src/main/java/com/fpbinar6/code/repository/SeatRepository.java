package com.fpbinar6.code.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.fpbinar6.code.models.Seat;

public interface SeatRepository extends JpaRepository<Seat, Integer> {
    
    @Query(value = "SELECT * FROM public.seat WHERE schedule_id = :schedule_id", nativeQuery = true)
    List<Seat> findByScheduleId(@Param("schedule_id") int scheduleId);
}
