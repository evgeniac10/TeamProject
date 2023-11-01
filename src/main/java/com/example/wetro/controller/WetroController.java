package com.example.wetro.controller;

import com.example.wetro.dto.Station;
import com.example.wetro.service.WetroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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

    @PostMapping("/main")
    public String mainInfo(@ModelAttribute("st") Station st){
        if (wetroService != null) {
            System.out.println("출발역 = " + st.getFrom()+ "  도착역 = "+st.getTo());

            wetroService.searchFromStation(st.getFrom());//입력받은 출발역
            wetroService.searchToStation(st.getTo());//입력받은 도착역
            wetroService.isStation();//
        } else {
            // wetroService가 null인 경우 예외 처리 또는 로깅 등을 수행
            // 예: logger.error("wetroService is null");
        }
        return "redirect:/wetro/main";
    }


}
