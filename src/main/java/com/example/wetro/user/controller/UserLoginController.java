package com.example.wetro.user.controller;

import com.example.wetro.user.dto.User;
import com.example.wetro.user.dto.UserLoginDto;
import com.example.wetro.user.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/wetro")
public class UserLoginController {

    private final UserService userService;

    @PostMapping("/login")
    public ResponseEntity<User> login(
            @RequestParam("userid") String userid,
            @RequestParam("password") String password
    ) {
        UserLoginDto dto = new UserLoginDto(userid, password);
        Optional<User> loginUser = userService.login(dto);

        if (loginUser.isPresent()) {
            // 로그인 성공 시 사용자 정보 반환
            return ResponseEntity.ok(loginUser.get());
        } else {
            // 로그인 실패 시 404 반환
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
