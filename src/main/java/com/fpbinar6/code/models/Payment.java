package com.fpbinar6.code.models;

import java.util.Random;

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
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Builder.Default;

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
    private Integer paymentId;

    @Generated("uuid")
    @Column(name = "booking_code", unique = true)
    private String bookingCode;

    @Column(name = "total_price")
    private int totalPrice;

    @Column(name = "payment_status", nullable = false)
    @Default
    private String paymentStatus = "not paid";

    @JoinColumn(name = "user_id", referencedColumnName = "id", unique = false)
    @ManyToOne(targetEntity = User.class, cascade = CascadeType.MERGE)
    private User user;

    @JoinColumn(name = "payment_method_id", referencedColumnName = "id", unique = false)
    @ManyToOne(targetEntity = PaymentMethod.class, cascade = CascadeType.MERGE)
    private PaymentMethod paymentMethod;

    public void setBookingCode(String bookingCode) {
        if (bookingCode == null || bookingCode.isEmpty()) {
            this.bookingCode = generateRandomString(6);
        } else {
            this.bookingCode = bookingCode;
        }
    }

    private String generateRandomString(int length) {
        String allowedCharacters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder sb = new StringBuilder(length);
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            int randomIndex = random.nextInt(allowedCharacters.length());
            sb.append(allowedCharacters.charAt(randomIndex));
        }
        return sb.toString();
    }

    public PaymentResponseDTO convertToResponse() {
        return PaymentResponseDTO.builder()
                .paymentId(this.paymentId)
                .bookingCode(this.bookingCode)
                .totalPrice(this.totalPrice)
                .paymentStatus(this.paymentStatus)
                .userId(this.user.getUserId())
                .paymentMethod(this.paymentMethod)
                .build();
    }

}
