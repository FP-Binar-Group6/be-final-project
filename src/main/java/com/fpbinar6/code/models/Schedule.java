package com.fpbinar6.code.models;

import java.sql.Timestamp;

import com.fpbinar6.code.models.dto.ScheduleResponseDTO;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name = "schedule")
public class Schedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int scheduleId;

    @Column(name = "departure_time")
    private Timestamp departureTime;

    @Column(name = "arrival_time")
    private Timestamp arrivalTime;

    @JoinColumn(name = "departure_airport_id", referencedColumnName = "id", unique = false)
    @ManyToOne(targetEntity = Airport.class, cascade = CascadeType.MERGE)
    private Airport departureAirport;

    @JoinColumn(name = "arrival_airport_id", referencedColumnName = "id", unique = false)
    @ManyToOne(targetEntity = Airport.class, cascade = CascadeType.MERGE)
    private Airport arrivalAirport;

    @JoinColumn(name = "airline_id", referencedColumnName = "id", unique = false)
    @ManyToOne(targetEntity = Airline.class, cascade = CascadeType.MERGE)
    private Airline airline;

    public ScheduleResponseDTO convertToResponse() {
        return ScheduleResponseDTO.builder()
                .scheduleId(this.scheduleId)
                .departureTime(this.departureTime)
                .arrivalTime(this.arrivalTime)
                .departureAirport(this.departureAirport)
                .arrivalAirport(this.arrivalAirport)
                .airline(this.airline)
                .build();
    }
}
