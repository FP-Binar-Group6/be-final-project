package com.fpbinar6.code.repository;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.fpbinar6.code.models.Schedule;

public interface ScheduleRepository extends JpaRepository<Schedule, Integer> {

    @Query("SELECT s FROM Schedule s " +
            "WHERE (cast(:departureTime as timestamp) IS NULL OR s.departureTime = cast(:departureTime as timestamp)) "
            +
            "AND (cast(:arrivalTime as timestamp) IS NULL OR s.arrivalTime = cast(:arrivalTime as timestamp)) " +
            "AND (:departureAirportId IS NULL OR s.departureAirport.id = :departureAirportId) " +
            "AND (:arrivalAirportId IS NULL OR s.arrivalAirport.id = :arrivalAirportId) " +
            "AND (:airlineId IS NULL OR s.airline.id = :airlineId)")
    List<Schedule> searchSchedules(
            @Param("departureTime") Timestamp departureTime,
            @Param("arrivalTime") Timestamp arrivalTime,
            @Param("departureAirportId") Integer departureAirportId,
            @Param("arrivalAirportId") Integer arrivalAirportId,
            @Param("airlineId") Integer airlineId);

    // @Query("SELECT s FROM Schedule s " +
    //         "WHERE (cast(:departureTime as timestamp) IS NULL OR s.departureTime = cast(:departureTime as timestamp)) "
    //         +
    //         "AND (cast(:arrivalTime as timestamp) IS NULL OR s.arrivalTime = cast(:arrivalTime as timestamp)) " +
    //         "AND (:departureAirportId IS NULL OR s.departureAirport.id = :departureAirportId) " +
    //         "AND (:arrivalAirportId IS NULL OR s.arrivalAirport.id = :arrivalAirportId) ")
    // List<Schedule> searchSchedulesWithoutAirlineId(
    //         @Param("departureTime") Timestamp departureTime,
    //         @Param("arrivalTime") Timestamp arrivalTime,
    //         @Param("departureAirportId") Integer departureAirportId,
    //         @Param("arrivalAirportId") Integer arrivalAirportId);

}
