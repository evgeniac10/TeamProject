package com.example.wetro.controller;

import com.example.wetro.dto.Station;
import com.example.wetro.service.WetroService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@Controller
@RequestMapping("/wetro")
public class WetroController {

    private WetroService wetroService;
    @Autowired
    public WetroController(WetroService wetroService) {
        this.wetroService = wetroService;
    }


    @GetMapping("/map")
    public String mapPage() {
        return "map";
    }

    @GetMapping("/excel")
    public String datas(){
        return "excel";
    }


    @GetMapping("/main")
    public String main(){
        return "main";
    }
    @GetMapping("/login")
    public String login(){
        return "login";
    }
    @GetMapping("/join")
    public String join(){
        return "join";
    }

//    @PostMapping("/main")
//    @ResponseBody
//    public ResponseEntity<Station> mainInfo(
//            @RequestParam("from") Integer from,
//            @RequestParam("to") Integer to
//    ) {
//        if (wetroService != null) {
//            System.out.println("출발역 = " + from + "  도착역 = " + to);
//
//            Integer depart = wetroService.searchFromStation(from);//입력받은 출발역
//            Integer arrive = wetroService.searchToStation(to);//입력받은 도착역
//            Station station = new Station();
//            station.setFrom(Integer.valueOf(String.valueOf(depart)));
//            station.setTo(Integer.valueOf(String.valueOf(arrive)));
//
//            return ResponseEntity.ok(station);
//        } else {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
//        }
    }


