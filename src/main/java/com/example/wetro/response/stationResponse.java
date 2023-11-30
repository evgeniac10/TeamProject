package com.example.wetro.response;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Getter;

import static org.springframework.http.HttpStatus.OK;

@Getter
@AllArgsConstructor
@JsonPropertyOrder({"isSuccess", "code", "message"})
public class stationResponse {

    private Boolean isSuccess;
    private int code;
    private String message;

    public static stationResponse success(String message) {
        return new stationResponse(true, OK.value(), message);
    }
}
