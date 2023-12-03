package com.example.wetro.response;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Getter;

import static org.springframework.http.HttpStatus.OK;

@Getter
@AllArgsConstructor
@JsonPropertyOrder({"isSuccess", "code", "message"})
public class bookmarkResponse {

    private Boolean isSuccess;
    private int code;
    private String message;

    public static bookmarkResponse success(String message) {
        return new bookmarkResponse(true, OK.value(), message);
    }

    public static bookmarkResponse fail(String message){
        return new bookmarkResponse(true,OK.value(),message);
    }
}
