package com.example.wetro.user.controller;

import com.example.wetro.jwt.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/wetro")
public class UserAuthController {

    private final JwtTokenProvider tokenProvider;
}
