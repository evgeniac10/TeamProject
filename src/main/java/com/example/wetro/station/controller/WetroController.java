package com.example.wetro.station.controller;

import com.example.wetro.station.service.WetroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/wetro")
public class WetroController {

    private WetroService wetroService;
    @Autowired
    public WetroController(WetroService wetroService) {
        this.wetroService = wetroService;
    }

    @GetMapping("/route")
    public String route(){
        return "route";
    }

    @GetMapping("/map")
    public String mapPage() {
        return "map";
    }

    @GetMapping("/routedetail")
    public String routeDetail(){
        return "routedetail";
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


