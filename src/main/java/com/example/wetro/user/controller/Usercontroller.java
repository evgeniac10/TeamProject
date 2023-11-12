package com.example.wetro.user.controller;


import com.example.wetro.user.dto.User;
import com.example.wetro.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
    public String loginPage(){
        return "login";
    }
    @PostMapping("/login")
    public String login(Model model
            , @RequestParam("userid") String userid,
                        @RequestParam("password") String password){
        User findUser = userService.findUser(userid, password);
        model.addAttribute("findUser", findUser);

        return "loginCheck";
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
