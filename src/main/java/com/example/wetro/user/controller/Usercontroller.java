package com.example.wetro.user.controller;


import com.example.wetro.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/wetro")
public class Usercontroller {
    private final UserService userService;

    @Autowired
    public Usercontroller(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/bookmark")
    public String bookmark(){
        return "bookmark";
    }


    @GetMapping("/login")
    public String login(){
        return "login";
    }

    @GetMapping("/join")
    public String joinPage(){
        return "join";
    }

    @PostMapping("/join")
    public String join(
            @RequestParam("userid") String userid,
            @RequestParam("password") String password,
            @RequestParam("email") String email
//            @ModelAttribute User user
    ){
        userService.saveUser(userid,password ,email);
        return "redirect:/wetro/main";
    }

}
