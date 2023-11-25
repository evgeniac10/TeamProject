package com.example.wetro.user.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.validation.constraints.NotEmpty;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class UserLoginDto {

    @NotEmpty
    String userid;

    @NotEmpty
    String password;
}
