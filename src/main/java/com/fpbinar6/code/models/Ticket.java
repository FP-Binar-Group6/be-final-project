package com.fpbinar6.code.models;

import java.sql.Date;

import com.fpbinar6.code.models.dto.TicketResponseDTO;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Ticket {
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ticket_id_seq")
    @SequenceGenerator(name = "ticket_id_seq", sequenceName = "ticket_id_seq", allocationSize = 1)
    @Column(name = "id")
    private Long ticketId;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name",nullable = false)
    private String lastName;

    @Column(name = "birth_date",nullable = false)
    private Date birthDate;

    @Column(name = "nationality",nullable = false)
    private String nationality;

    @Column(name = "id_card_number",nullable = false)
    private String idCardNumber;

    @JoinColumn(name = "seat_id", referencedColumnName = "id")
    @OneToOne(targetEntity = Seat.class, cascade = CascadeType.MERGE)
    private Seat seat;

    public TicketResponseDTO convertToResponse(){
        return TicketResponseDTO.builder()
        .ticketId(this.ticketId)
        .title(this.title)
        .firstName(this.firstName)
        .lastName(this.lastName)
        .birthDate(this.birthDate)
        .nationality(this.nationality)
        .idCardNumber(this.idCardNumber)
        .seat(this.seat)
        .build();
    }
}
