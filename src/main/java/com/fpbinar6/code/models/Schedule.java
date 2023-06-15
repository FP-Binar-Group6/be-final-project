package com.fpbinar6.code.models;

import java.sql.Timestamp;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data 
@AllArgsConstructor
@NoArgsConstructor
@Entity
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

    @JoinColumn(name = "departure_airport_id", referencedColumnName = "id")
    @OneToOne(targetEntity = Airport.class, cascade = CascadeType.ALL)
    private Airport departureAirport;

    @JoinColumn(name = "arrival_airport_id", referencedColumnName = "id")
    @OneToOne(targetEntity = Airport.class, cascade = CascadeType.ALL)
    private Airport arrivalAirport;

    @JoinColumn(name = "airline_id", referencedColumnName = "id")
    @OneToOne(targetEntity = Airline.class, cascade = CascadeType.ALL)
    private Airline airline;
}
