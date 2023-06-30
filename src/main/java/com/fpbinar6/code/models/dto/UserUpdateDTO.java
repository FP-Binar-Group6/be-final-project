package com.fpbinar6.code.models.dto;

import com.fpbinar6.code.models.User;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserUpdateDTO {
    private int userId;
    private String name;
    private String email;
    private String phoneNumber;

    public User toUser(){
        return User.builder()
        .userId(this.userId)
        .name(this.name)
        .email(this.email)
        .phoneNumber(this.phoneNumber)
        .build();
    }
}
