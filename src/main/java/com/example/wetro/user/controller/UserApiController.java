package com.example.wetro.user.controller;

import com.example.wetro.user.dto.User;
import com.example.wetro.user.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/wetro")
public class UserApiController {

    @Autowired
    private UserServiceImpl userService;

    @Autowired
    public UserApiController(UserServiceImpl userService) {
        this.userService = userService;
    }
    //아이디 중복 체크
    @PostMapping("/checkDuplicateId")
    public ResponseEntity<String> checkDuplicateId(@RequestParam("userid") String userid) {
        if (userService.isUserIdDuplicate(userid)) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("아이디 중복");
        } else {
            return ResponseEntity.ok("아이디 사용 가능");
        }
    }
    //인증번호 발송
    @PostMapping("/sendVerificationCode")
    public ResponseEntity<String> sendVerificationCode(@RequestParam("email") String email) {
        String verificationCode = userService.generateAndSendVerificationCode(email);
        if (verificationCode != null) {
            return ResponseEntity.ok("인증번호 발송 성공");
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("인증번호 발송 실패");
        }
    }

    //인증번호 확인
    @PostMapping("/verifyCode")
    public ResponseEntity<String> verifyCode(@RequestParam("email") String email, @RequestParam("code") String code) {
        if (userService.verifyCode(email, code)) {
            return ResponseEntity.ok("인증번호 확인 성공");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("인증번호 확인 실패");
        }
    }

    //가입 성공
    @PostMapping("/signup")
    public ResponseEntity<String> signUp(@RequestParam("userid") String userid,
                                         @RequestParam("password") String password,
                                         @RequestParam("email") String email) {
        if (userService.signUp(userid, password, email)) {
            return ResponseEntity.ok("가입 성공");
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("가입 실패");
        }
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
