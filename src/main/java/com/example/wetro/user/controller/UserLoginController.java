package com.example.wetro.user.controller;

import com.example.wetro.user.dto.User;
import com.example.wetro.user.dto.UserLoginDto;
import com.example.wetro.user.jwt.JwtTokenProvider;
import com.example.wetro.user.service.UserService;
import com.example.wetro.response.Response;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

import static com.example.wetro.response.Response.*;
import static com.example.wetro.response.SuccessMessage.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/wetro")
public class UserLoginController {

    private final UserService userService;
    private final JwtTokenProvider tokenProvider;


    @PostMapping("/login")
    public Response login(@RequestBody @Valid UserLoginDto loginDto) {
        log.info("로그인 컨트롤러");

        UserLoginDto dto = new UserLoginDto(loginDto.getUserid(), loginDto.getPassword());
        log.info("현재 입력된 유저 아이디 ={} 그리고 유저 비밀번호 = {}",loginDto.getUserid(),loginDto.getPassword());
        Optional<User> loginUser = userService.login(dto);
        log.info("DB에서 꺼내온 유저 ={}",loginUser.toString());

        if (loginUser.isPresent()) {
            log.info("로그인 성공");
            String token = tokenProvider.createToken(null);
            return success(SUCCESS_TO_LOGIN,token);
        } else {
            log.info("로그인 실패");
            return fail(HttpStatus.NOT_FOUND,FAIL_TO_LOGIN);
        }
    }
}
