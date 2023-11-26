package com.example.wetro.user.controller;

import com.example.wetro.config.JwtTokenProvider;
import com.example.wetro.user.dto.User;
import com.example.wetro.user.dto.UserLoginDto;
import com.example.wetro.user.service.UserService;
import com.example.wetro.response.Response;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

import static com.example.wetro.response.Response.*;
import static com.example.wetro.response.SuccessMessage.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/wetro")
public class UserLoginController {

    private final UserService userService;
    private final JwtTokenProvider tokenProvider;

    @PostMapping("/login")
    public Response login(@RequestBody @Valid UserLoginDto loginDto) {
        System.out.println("login 컨트롤러");
        UserLoginDto dto = new UserLoginDto(loginDto.getUserid(), loginDto.getPassword());
        Optional<User> loginUser = userService.login(dto);

        if (loginUser.isPresent()) {
            System.out.println("로그인 성공");
            String token = tokenProvider.createToken(loginDto.getUserid());
            return success(SUCCESS_TO_LOGIN,token);
        } else {
            System.out.println("로그인 실패");
            return fail(HttpStatus.NOT_FOUND,FAIL_TO_LOGIN);
        }
    }
}
