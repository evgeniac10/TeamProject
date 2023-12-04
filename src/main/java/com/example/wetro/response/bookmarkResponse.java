package com.example.wetro.response;

import com.example.wetro.bookmark.dto.BookMark;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

import static org.springframework.http.HttpStatus.OK;

@Getter
@AllArgsConstructor
@JsonPropertyOrder({"isSuccess", "code", "message","bookMarkList"})
public class bookmarkResponse {

    private Boolean isSuccess;
    private int code;
    private String message;
    private List<BookMark> bookMarkList;

    public static bookmarkResponse success(String message) {
        return new bookmarkResponse(true, OK.value(), message , null);
    }
    public static bookmarkResponse success(String message, List<BookMark> bookMarkList) {
        return new bookmarkResponse(true, OK.value(), message,bookMarkList);
    }

    public static bookmarkResponse fail(String message){
        return new bookmarkResponse(true,OK.value(),message , null);
    }
}
