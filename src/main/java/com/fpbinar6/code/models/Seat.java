package com.fpbinar6.code.models;

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
@Entity
@Table(name = "seat")
@AllArgsConstructor
@NoArgsConstructor
public class Seat {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int seatId;

    @Column(name = "seat_number")
    private String seatNumber;

    @Column(name = "is_picked")
    private boolean isPicked;

    @JoinColumn(name = "class_id", referencedColumnName = "id")
    @OneToOne(targetEntity = Class.class, cascade = CascadeType.ALL)
    private Class kelas;

    @JoinColumn(name = "schedule_id", referencedColumnName = "id")
    @OneToOne(targetEntity = Schedule.class, cascade = CascadeType.ALL)
    private Schedule schedule;
}
