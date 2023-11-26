package com.example.wetro.response;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import static org.springframework.http.HttpStatus.OK;
@Getter
@AllArgsConstructor
public class Response {
    private String message;

    public static Response success(HttpStatus status, String message){
        return new Response(message);
    }
    public static Response fail(HttpStatus status,String message) {
        return new Response(message);
    }
}
