package com.example.wetro.station.controller;

import com.example.wetro.response.Response;
import com.example.wetro.station.dto.Station;
import com.example.wetro.station.dto.StationDto;
import com.example.wetro.station.service.WetroService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.example.wetro.response.Response.success;

@Slf4j
@RestController
@RequestMapping("/wetro")
@RequiredArgsConstructor
public class WetroApiController {

    private final WetroService wetroService;

    @PostMapping(value = "/search")
    @ResponseBody
    public Response Info(@RequestBody StationDto stationDto) {

        return success()
    }
}

