package com.example.wetro.response;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import static org.springframework.http.HttpStatus.OK;

@Getter
@AllArgsConstructor
@JsonPropertyOrder({"isSuccess", "code", "message", "token", "tokenId"})
public class Response {

    private Boolean isSuccess;
    private int code;
    private String message;
    private Object token;
    private Long tokenId; // Add tokenId field

    public static Response success(String message) {
        return new Response(true, OK.value(), message, null, null);
    }

    public static Response success(String message, Object token) {
        return new Response(true, OK.value(), message, token, null);
    }

    public static Response success(String message, Object token, Long tokenId) {
        return new Response(true, OK.value(), message, token, tokenId);
    }

    public static Response fail(HttpStatus status, String message) {
        return new Response(false, status.value(), message, null, null);
    }
}
