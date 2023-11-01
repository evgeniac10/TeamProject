package com.example.wetro.controller;

import com.example.wetro.dto.Station;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/wetro")
public class WetroController {

    @GetMapping("/main")
    public String main(){
        return "main";
    }

    @PostMapping("/main")
    public String mainInfo(Station st){

        Station station = new Station();
        station.setFrom(st.getFrom());
        station.setTo(st.getTo());
        System.out.println("출발역 = " + station.getFrom()+ "  도착역 = "+station.getTo());

        return "redirect:/wetro/main";
    }


}
