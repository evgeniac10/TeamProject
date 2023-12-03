package com.example.wetro.response;

import com.example.wetro.station.dto.Infolists;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

import static org.springframework.http.HttpStatus.OK;

@Getter
@AllArgsConstructor
@JsonPropertyOrder({"isSuccess", "code", "message"})
public class stationResponse {

    private Boolean isSuccess;
    private int code;
    private String message;
    private List<Infolists> infolists;

    public static stationResponse success(String message) {
        return new stationResponse(true, OK.value(), message,null);
    }
    public static stationResponse success(String message,List<Infolists> infolists) {
        return new stationResponse(true, OK.value(), message,infolists);
    }
}
