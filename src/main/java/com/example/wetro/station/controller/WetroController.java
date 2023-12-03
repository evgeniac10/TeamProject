package com.example.wetro.station.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequiredArgsConstructor
@Controller
@RequestMapping("/wetro")
public class WetroController {


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


