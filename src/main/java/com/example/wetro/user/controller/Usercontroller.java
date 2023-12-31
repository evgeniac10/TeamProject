package com.example.wetro.user.controller;

import com.example.wetro.user.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/wetro")
public class Usercontroller {
    private final UserServiceImpl userService;

    @Autowired
    public Usercontroller(UserServiceImpl userService) {
        this.userService = userService;
    }

    @GetMapping("/bookmark")
    public String bookmark(){
        return "bookmark";
    }


    @GetMapping("/login")
    public String loginPage(){
        return "login";
    }


    @GetMapping("/join")
    public String joinPage(){
        return "join";
    }

    @GetMapping("/timer")
    public String timer(){
        return "timer";
    }
}
