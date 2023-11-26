package com.example.wetro.user.controller;

import com.example.wetro.response.Response;
import com.example.wetro.user.dto.EmailRequest;
import com.example.wetro.user.dto.User;
import com.example.wetro.user.dto.VerificationRequest;
import com.example.wetro.user.service.EmailService;
import com.example.wetro.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static com.example.wetro.response.Response.*;
import static com.example.wetro.response.SuccessMessage.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/wetro")
public class UserJoinController {

    @Autowired
    private final UserService userService;
    @Autowired
    private final EmailService emailService;

    private String savedVerificationCode;

    //id 중복 확인
    @GetMapping("/checkDuplicateId/{userid}")
    public ResponseEntity<String> checkDuplicateId(@PathVariable String userid) {
        if (userService.isExistId(userid)) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("아이디 중복");
        } else {
            return ResponseEntity.ok("아이디 사용 가능");
        }
    }

    //이메일 인증 코드
    @PostMapping("/sendVerificationCode")
    public Response sendVerificationCode(@RequestBody EmailRequest emailAddress) throws Exception {
        savedVerificationCode = emailService.sendSimpleMessage(emailAddress.getEmailAddress());
        // 이메일 발송 로직 수행
        return success(SUCCESS_TO_SEND_VERI);
    }

    //이메일 인증 코드 확인
    @PostMapping("/verify")
    public ResponseEntity<String> verifyCode(@RequestBody VerificationRequest verificationCode) {
        String inputCode = verificationCode.getVerificationCode();

        if (inputCode.equals(savedVerificationCode)) {
            return ResponseEntity.ok("이메일 인증 성공");
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("이메일 인증 실패");
        }
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
