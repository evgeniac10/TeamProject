package com.example.wetro.response;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import static org.springframework.http.HttpStatus.OK;

@Getter
@AllArgsConstructor
@JsonPropertyOrder({"isSuccess", "code", "message", "token", "tokenId"})
public class tokenResponse {

    private Boolean isSuccess;
    private int code;
    private String message;
    private Object token;
    private Long tokenId; // Add tokenId field

    public static tokenResponse success(String message) {
        return new tokenResponse(true, OK.value(), message, null, null);
    }

    public static tokenResponse success(String message, Object token) {
        return new tokenResponse(true, OK.value(), message, token, null);
    }

    public static tokenResponse success(String message, Object token, Long tokenId) {
        return new tokenResponse(true, OK.value(), message, token, tokenId);
    }

    public static tokenResponse fail(HttpStatus status, String message) {
        return new tokenResponse(false, status.value(), message, null, null);
    }
}
