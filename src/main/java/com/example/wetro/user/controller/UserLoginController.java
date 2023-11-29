package com.example.wetro.user.controller;

import com.example.wetro.user.dto.User;
import com.example.wetro.user.dto.UserLoginDto;
import com.example.wetro.user.jwt.JwtTokenProvider;
import com.example.wetro.user.service.UserService;
import com.example.wetro.response.Response;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
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
    private final AuthenticationManagerBuilder authenticationManagerBuilder;

    @PostMapping("/login")
    public Response login(@RequestBody @Valid UserLoginDto loginDto) {
        log.info("로그인 컨트롤러");

        List<GrantedAuthority> authorities = new ArrayList<>();
        // authorities를 설정할 필요에 따라서 UserRepository 등을 통해 DB에서 가져와서 설정
        authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(loginDto.getUserid(), loginDto.getPassword(),authorities);
        log.info("인증토큰 = {}",authenticationToken.getAuthorities());

        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
        log.info("authentication ={}",authentication.getAuthorities());

        SecurityContextHolder.getContext().setAuthentication(authentication);

        UserLoginDto dto = new UserLoginDto(loginDto.getUserid(), loginDto.getPassword());
        log.info("현재 입력된 유저 아이디 ={} 그리고 유저 비밀번호 = {}",loginDto.getUserid(),loginDto.getPassword());
        Optional<User> loginUser = userService.login(dto);
        log.info("DB에서 꺼내온 유저 ={}",loginUser.toString());

        if (loginUser.isPresent()) {
            log.info("로그인 성공");
            String token = tokenProvider.createToken(authentication);
            return success(SUCCESS_TO_LOGIN,token);
        } else {
            log.info("로그인 실패");
            return fail(HttpStatus.NOT_FOUND,FAIL_TO_LOGIN);
        }
    }
}
