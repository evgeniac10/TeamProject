package com.example.wetro.station.controller;

import com.example.wetro.response.stationResponse;
import com.example.wetro.dijkstra.*;
import com.example.wetro.station.dto.StationDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.example.wetro.response.Message.*;
import static com.example.wetro.response.stationResponse.*;

@Slf4j
@RestController
@RequestMapping("/wetro")
@RequiredArgsConstructor
public class WetroApiController {

    private final Node node;

    @PostMapping(value = "/search")
    @ResponseBody
    public stationResponse Info(@RequestBody StationDto stationDto) {

        log.info("입력한 출발역 = {}",stationDto.getFrom());
        log.info("입력한 도착역 = {}",stationDto.getTo());

        Node.result result = Node.calculateShortestCost("123", "601");


        log.info("다익스트라 결과 = {} , {}, {} ", result.getTime(), result.getCost(),result.getPath());

        return success(SUCCESS_TO_INFO);
    }
}

