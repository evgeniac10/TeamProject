package com.example.wetro.user.controller;

import com.example.wetro.response.tokenResponse;
import com.example.wetro.user.dto.*;
import com.example.wetro.user.service.EmailService;
import com.example.wetro.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import java.util.HashSet;
import java.util.Set;

import static com.example.wetro.response.tokenResponse.*;
import static com.example.wetro.response.Message.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/wetro/join")
public class UserJoinController {

    private final UserService userService;
    private final EmailService emailService;
    private final PasswordEncoder passwordEncoder;

    private String savedVerificationCode;

    //id 중복 확인
    @PostMapping("/checkDuplicateId/{userid}")
    public tokenResponse checkDuplicateId(@PathVariable String userid) {
        if (userService.isExistId(userid)) {
            return fail(HttpStatus.BAD_REQUEST,FAIL_USE_ID);
        } else {
            return success(SUCCESS_UES_ID);
        }
    }

    //이메일 인증 코드
    @PostMapping("/sendVerificationCode")
    public tokenResponse sendVerificationCode(@RequestBody EmailRequest emailAddress) throws Exception {
        savedVerificationCode = emailService.sendSimpleMessage(emailAddress.getEmailAddress());
        // 이메일 발송 로직 수행
        return success(SUCCESS_TO_SEND_VERI);
    }

    //이메일 인증 코드 확인
    @PostMapping("/verify")
    public tokenResponse verifyCode(@RequestBody VerificationRequest verificationCode) {
        String inputCode = verificationCode.getVerificationCode();
        if (inputCode.equals(savedVerificationCode)) {
            return success(SUCCESS_TO_VERI_CODE);
        } else {
            return success(FAIL_TO_VERI_CODE);
        }
    }

    // 가입 처리
    @PostMapping("/signup")
    public tokenResponse signUp(@RequestBody @Valid User userDto , BindingResult bindingResult) {

        if(bindingResult.hasErrors())//Valid로 유효성 검사를 했는데 입력해야하는 양식에 맞지 않는 경우 BAD_REQUEST를 반환한다.
            return fail(HttpStatus.BAD_REQUEST, VALID_INPUT_REQUIRED);

        if (userService.isExistId(userDto.getUserid()))
            return fail(HttpStatus.BAD_REQUEST,FAIL_USE_ID);
        if(userService.isExistEmail(userDto.getEmail()))
            return fail(HttpStatus.BAD_REQUEST,FAIL_USE_EMAIL);

        User user = new User();
        user.setUserid(userDto.getUserid());
        String encodePassword = passwordEncoder.encode(userDto.getPassword());
        user.setPassword(encodePassword);
        user.setEmail(userDto.getEmail());

        Set<Authority> authorities = new HashSet<>();
        user.setAuthorities(authorities);

        userService.save(user);

        return success( SUCCESS_TO_SIGN);
    }

}
