package com.example.wetro.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/wetro")
public class WetroController {

    @GetMapping("/main")
    public String main(){
        return "main";
    }




}
