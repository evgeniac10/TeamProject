package com.example.wetro.user.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/wetro")
public class usercontroller {

    @GetMapping("/bookmark")
    public String bookmark(){
        return "bookmark";
    }


    @GetMapping("/login")
    public String login(){
        return "login";
    }
    @GetMapping("/join")
    public String join(){
        return "join";
    }

}
