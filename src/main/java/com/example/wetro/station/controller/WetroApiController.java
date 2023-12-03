package com.example.wetro.station.controller;

import com.example.wetro.response.stationResponse;
import com.example.wetro.dijkstra.*;
import com.example.wetro.station.dto.StationDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.example.wetro.response.Message.*;
import static com.example.wetro.response.stationResponse.*;

@Slf4j
@RestController
@RequestMapping("/wetro")
@RequiredArgsConstructor
public class WetroApiController {



    @PostMapping(value = "/search")
    @PreAuthorize("hasAuthority('USER')")
    @ResponseBody
    public stationResponse Info(@RequestBody StationDto stationDto) {

        log.info("입력한 출발역 = {}",stationDto.getFrom());
        log.info("입력한 도착역 = {}",stationDto.getTo());

        Node.result result1 = Node.calculateShortestTime(stationDto.getFrom(), stationDto.getTo());//최소시간
        Node.result result2 = Node.calculateShortestCost(stationDto.getFrom(), stationDto.getTo());//최소비용
        Node.resultT resultT = Node.calculateMinTransfer(stationDto.getFrom(), stationDto.getTo());//최소환승

        log.info("다익스트라 결과 = 최소 시간 {} , 최소 비용 {} , 환승 횟수 {}"
                , result1.getTime()
                , result2.getCost()
                ,resultT.getTransferCount());


        return success(SUCCESS_TO_INFO);
    }
}

