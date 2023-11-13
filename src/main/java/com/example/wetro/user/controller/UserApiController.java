package com.example.wetro.user.controller;

import com.example.wetro.user.dto.EmailRequest;
import com.example.wetro.user.dto.User;
import com.example.wetro.user.service.EmailService;
import com.example.wetro.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/wetro")
public class UserApiController {

    @Autowired
    private final UserService userService;
    @Autowired
    private final EmailService emailService;

    //id 중복 확인
    @GetMapping("/checkDuplicateId/{userid}")
    public ResponseEntity<String> checkDuplicateId(@PathVariable String userid) {
        if (userService.isExistId(userid)) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("아이디 중복");
        } else {
            return ResponseEntity.ok("아이디 사용 가능");
        }
    }
    @PostMapping("/sendVerificationCode")
    public ResponseEntity<String> sendVerificationCode(@RequestBody EmailRequest emailAddress) throws Exception {
        String input = emailAddress.getEmailAddress();
        // emailRequest에는 이메일 주소 등이 포함될 것으로 가정
        String confirm = emailService.sendSimpleMessage(input);
        // 이메일 발송 로직 수행
        return ResponseEntity.ok("이메일 인증번호 발송 성공");
    }

    // 가입 처리
    @PostMapping("/signup")
    public ResponseEntity<User> signUp(@RequestBody @Valid User userDto , BindingResult bindingResult) {

        if(bindingResult.hasErrors())//Valid로 유효성 검사를 했는데 입력해야하는 양식에 맞지 않는 경우 BAD_REQUEST를 반환한다.
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        if (userService.isExistId(userDto.getUserid()))
            return ResponseEntity.status(HttpStatus.CONFLICT).build();

        if(userService.isExistEmail(userDto.getEmail()))
            return ResponseEntity.status(HttpStatus.CONFLICT).build();

        User user = new User();
        user.setUserid(userDto.getUserid());
        user.setPassword(userDto.getPassword());
        user.setEmail(userDto.getEmail());

        User saveUser = userService.save(user);

        return new ResponseEntity<>(saveUser, HttpStatus.CREATED);
    }

}
