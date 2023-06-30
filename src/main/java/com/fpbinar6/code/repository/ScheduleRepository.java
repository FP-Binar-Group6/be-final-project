package com.fpbinar6.code.repository;

import java.sql.Timestamp;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.fpbinar6.code.models.Schedule;

@Repository
public interface ScheduleRepository extends JpaRepository<Schedule, Integer> {

        @Query("SELECT s FROM Schedule s " +
                        "WHERE (cast(:departureTime as timestamp) IS NULL OR s.departureTime >= cast(:departureTimeStart as timestamp) AND s.departureTime < cast(:departureTimeEnd as timestamp)) "
                        +
                        "AND (:departureAirportId IS NULL OR s.departureAirport.id = :departureAirportId) " +
                        "AND (:arrivalAirportId IS NULL OR s.arrivalAirport.id = :arrivalAirportId) " +
                        "AND (:airlineId IS NULL OR s.airline.id = :airlineId)" + "ORDER BY s.departureTime ASC")
        List<Schedule> searchSchedules(
                        @Param("departureTime") Timestamp departureTime,
                        @Param("departureTimeStart") Timestamp departureTimeStart,
                        @Param("departureTimeEnd") Timestamp departureTimeEnd,
                        @Param("departureAirportId") Integer departureAirportId,
                        @Param("arrivalAirportId") Integer arrivalAirportId,
                        @Param("airlineId") Integer airlineId);

}
