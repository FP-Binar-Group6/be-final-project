package com.fpbinar6.code.models;

import java.util.List;

import com.fpbinar6.code.models.dto.PaymentResponseDTO;
import jakarta.annotation.Generated;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "payment")
public class Payment {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
    private int paymentId;

    @Generated("uuid")
    @Column(name = "booking_code", nullable = false, unique = true)
    private String bookingCode;

    @JoinColumn(name = "ticket_id", referencedColumnName = "id", unique = false)
    @OneToMany(targetEntity = Ticket.class, cascade = CascadeType.MERGE)
    private List<Ticket> ticket;

    @Column(name = "total_price", nullable = false)
    private int totalPrice;

    @Column(name = "payment_status", nullable = false)
    private String paymentStatus;

    @JoinColumn(name = "user_id", referencedColumnName = "id", unique = false)
    @ManyToOne(targetEntity = User.class, cascade = CascadeType.MERGE)
    private User user;

    @JoinColumn(name = "payment_method_id", referencedColumnName = "id", unique = false)
    @ManyToOne(targetEntity = PaymentMethod.class, cascade = CascadeType.MERGE)
    private PaymentMethod paymentMethod;

    public PaymentResponseDTO convertToResponse(){
        return PaymentResponseDTO.builder()
                .paymentId(this.paymentId)
                .bookingCode(this.bookingCode)
                .ticket(this.ticket)
                .totalPrice(this.totalPrice)
                .paymentStatus(this.paymentStatus)
                .userId(this.user.getUserId())
                .paymentMethod(this.paymentMethod)
                .build();
    } 

}
