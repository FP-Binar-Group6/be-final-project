package com.fpbinar6.code.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.fpbinar6.code.models.Schedule;

public interface ScheduleRepository extends JpaRepository<Schedule, Integer> {
    //@Query(value = "SELECT * FROM public.schedule s WHERE s.departure_airport_id LIKE % %", nativeQuery = true)
}
