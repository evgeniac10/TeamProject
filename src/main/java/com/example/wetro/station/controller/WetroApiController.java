package com.example.wetro.station.controller;

import com.example.wetro.response.stationResponse;
import com.example.wetro.dijkstra.*;
import com.example.wetro.station.dto.StationDto;
import com.example.wetro.station.service.WetroService;
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

    private final WetroService wetroService;
    private final Node node;

    @PostMapping(value = "/search")
    @ResponseBody
    public stationResponse Info(@RequestBody StationDto stationDto) {

        log.info("입력한 출발역 = {}",stationDto.getFrom());
        log.info("입력한 도착역 = {}",stationDto.getTo());

        Node.result result = Node.calculateShortestPath("101","123");
        Integer distance = result.getDistance();
        List<Node> shortestPath = result.getShortestPath();
        shortestPath.get(0);
        log.info("다익스트라 결과 = {} ,{} ",distance,shortestPath);

        return success(SUCCESS_TO_INFO);
    }
}

