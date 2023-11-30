package com.example.wetro.user.dto;

import lombok.*;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TokenDto {

    @NotNull
    Long tokenId;
}
