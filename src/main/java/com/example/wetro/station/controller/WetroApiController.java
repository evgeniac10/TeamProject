package com.example.wetro.station.controller;

import com.example.wetro.station.dto.Station;
import com.example.wetro.station.service.WetroService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/wetro")
public class WetroApiController {

    private WetroService wetroService;
    @Autowired
    public WetroApiController(WetroService wetroService) {
        this.wetroService = wetroService;
    }

    @RequestMapping(value = "/api", method = { RequestMethod.POST,RequestMethod.GET})
    @ResponseBody
    public ResponseEntity<Station> mainInfo(
            @RequestParam("from") String from,
            @RequestParam("to") String to
    ) {
        if (wetroService != null) {
            System.out.println("출발역 = " + from + "  도착역 = " + to);

            Integer depart = wetroService.searchFromStation(Integer.parseInt(from));
            Integer arrive = wetroService.searchToStation(Integer.parseInt(to));

            Station station = new Station();
            station.setFrom(Integer.valueOf(String.valueOf(depart)));
            station.setTo(Integer.valueOf(String.valueOf(arrive)));


            return ResponseEntity.ok(station);
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

}
