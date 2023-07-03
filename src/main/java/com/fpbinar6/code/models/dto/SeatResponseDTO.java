package com.fpbinar6.code.models.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SeatResponseDTO {
    private int seatId;
    private String seatNumber;
    private boolean isPicked;
}
