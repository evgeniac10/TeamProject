package com.example.wetro.station.dto;


import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Station {

    private Integer from;   //  출발역
    private Integer to;  //  도착역
    private Integer time;   //  시간
    private Integer distance;//거리
    private Integer cost;   //비용

}
