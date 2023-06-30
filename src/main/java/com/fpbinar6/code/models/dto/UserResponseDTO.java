package com.fpbinar6.code.models.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserResponseDTO {
    private int userId;
    private String name;
    private String email;
    private String phoneNumber;
    private String role;
}
