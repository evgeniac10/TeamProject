package com.example.wetro.controller;

import com.example.wetro.dto.Station;
import com.example.wetro.service.WetroService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/wetro")
public class WetroController {

    private WetroService wetroService;

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

    @PostMapping("/main")
    public String mainInfo(Station st){


        wetroService.searchFromStation(st.getFrom());//입력받은 출발역
        wetroService.searchToStation(st.getTo());//입력받은 도착역
        //입력받은 출발역,도착역이 제대로 입력을 했다면
        //입력이 제대로 안됐다면 예외처리해주기
        wetroService.isStation();//
//        System.out.println("출발역 = " + station.getFrom()+ "  도착역 = "+station.getTo());

        return "redirect:/wetro/main";
    }


}
