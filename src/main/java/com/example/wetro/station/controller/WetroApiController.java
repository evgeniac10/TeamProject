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

        Node.result result = Node.calculateShortestTime(stationDto.getFrom(), stationDto.getTo());
        Integer time = result.getTime();
        Integer cost = result.getCost();
        String path = result.getPath();

        log.info("다익스트라 결과 = {} , {} ,{} ", time, cost,path);

        return success(SUCCESS_TO_INFO);
    }
}

