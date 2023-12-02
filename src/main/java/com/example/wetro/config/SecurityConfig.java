package com.example.wetro.config;

import com.example.wetro.jwt.JwtAccessDeniedHandler;
import com.example.wetro.jwt.JwtAuthenticationEntryPoint;
import com.example.wetro.jwt.JwtSecurityConfig;
import com.example.wetro.jwt.JwtTokenProvider;
import com.example.wetro.user.service.SecurityCustomUserDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final JwtTokenProvider tokenProvider;
    private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
    private final JwtAccessDeniedHandler jwtAccessDeniedHandler;
    private final SecurityCustomUserDetailService securityCustomUserDetailService; // 사용자 정보를 가져올 서비스

    @Value("${jwt.secret}")
    private String secretKey;

    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(securityCustomUserDetailService)
                .passwordEncoder(passwordEncoder());
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web
                .ignoring()
                .antMatchers("/css/**")
                .antMatchers("/img/**")
                .antMatchers("/js/**");
    }

    protected void configure(HttpSecurity http) throws Exception {
        // 특정 URL은 인증을 거치지 않도록 설정
        http
                    .exceptionHandling()
                    .authenticationEntryPoint(jwtAuthenticationEntryPoint)
                    .accessDeniedHandler(jwtAccessDeniedHandler)

                .and()

                    .authorizeRequests()
                    .antMatchers("/wetro/**").permitAll()
                    .anyRequest().authenticated()//다른 모든 요청에 대한 인증을 요구합니다.

                .and()

                    .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.NEVER)
                .and()

                    .apply(new JwtSecurityConfig(tokenProvider)); // JwtConfigurer를 적용하여 JWT 사용 설정

    }


}
