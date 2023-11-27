package com.example.wetro.user.jwt;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JwtTokenFilter extends OncePerRequestFilter {

    private final JwtTokenProvider tokenProvider;

    public JwtTokenFilter(JwtTokenProvider jwtTokenProvider) {
        this.tokenProvider = jwtTokenProvider;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = tokenProvider.resolveToken(request);

        try {
            if (token != null && tokenProvider.validateToken(token)) {
                // 토큰이 유효하면 Spring Security에 인증 정보 설정
                SecurityContextHolder.getContext().setAuthentication(tokenProvider.getAuthentication(token));
            }
        } catch (AuthenticationException ex) {
            // 토큰이 유효하지 않은 경우
            SecurityContextHolder.clearContext();
        }

        filterChain.doFilter(request, response);
    }
}
