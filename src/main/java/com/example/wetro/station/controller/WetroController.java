package com.example.wetro.station.controller;

import com.example.wetro.station.service.WetroService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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
    }


