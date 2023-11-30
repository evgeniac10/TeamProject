package com.example.wetro.user.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.validation.constraints.NotEmpty;

@Data
@AllArgsConstructor
public class UserLoginDto {

    @NotEmpty
    String userid;

    @NotEmpty
    String password;
}
