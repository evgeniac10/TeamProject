package com.example.wetro.user.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class usercontroller {


    @GetMapping("/login")
    public String login(){
        return "login";
    }
    @GetMapping("/join")
    public String join(){
        return "join";
    }

}
