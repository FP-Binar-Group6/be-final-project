package com.fpbinar6.code.models;

import com.fpbinar6.code.models.dto.SeatResponseDTO;

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
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Builder
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
    @OneToOne(targetEntity = Class.class, cascade = CascadeType.MERGE)
    private Class kelas;

    @JoinColumn(name = "schedule_id", referencedColumnName = "id")
    @OneToOne(targetEntity = Schedule.class, cascade = CascadeType.MERGE)
    private Schedule schedule;

    public SeatResponseDTO convertToResponse(){
        return SeatResponseDTO.builder()
                .seatId(this.seatId)
                .seatNumber(this.seatNumber)
                .kelas(this.kelas)
                .schedule(this.schedule)
                .isPicked(this.isPicked)
                .build();
    }
}