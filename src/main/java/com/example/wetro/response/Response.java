package com.example.wetro.response;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import static org.springframework.http.HttpStatus.OK;

@Getter
@AllArgsConstructor
@JsonPropertyOrder({"isSuccess", "code", "message", "data"})
public class Response {

    private Boolean isSuccess;
    private int code;
    private String message;
    private Object data;

    public static Response success(String message) {
        return new Response(true, OK.value(), message, null);
    }
    public static Response success(String message, Object data) {
        return new Response(true, OK.value(), message, data);
    }
    public static Response fail(HttpStatus status,String message) {
        return new Response(false, status.value(), message, null);
    }
}
