package com.example.wetro.user.controller;

import com.example.wetro.user.dto.User;
import com.example.wetro.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/wetro")
public class UserApiController {
    private UserService userService;
    @Autowired
    public UserApiController(UserService userService) {
        this.userService = userService;
    }
    @RequestMapping(value = "/user/api", method = { RequestMethod.POST,RequestMethod.GET})
    @ResponseBody
    public ResponseEntity<User> mainInfo(
            @RequestParam("userid") String userid,
            @RequestParam("password") String password
    ) {
        try {
            User findUser = userService.findUser(userid, password);
            if (findUser != null) {
                return ResponseEntity.ok(findUser);
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

}
