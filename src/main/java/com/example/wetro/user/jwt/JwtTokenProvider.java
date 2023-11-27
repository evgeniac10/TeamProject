package com.example.wetro.user.jwt;

import com.example.wetro.user.service.UserService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@Component
@RequiredArgsConstructor
public class JwtTokenProvider {

    @Value("${jwt.secret}")
    private String key;
    private final Long ms = 1000 * 60 * 60L;

    private final UserService userService;

    public String createToken(String userId) {
        Claims claims = Jwts.claims().setSubject(userId);

        return Jwts.builder()
                            .setClaims(claims)
                            .signWith(SignatureAlgorithm.HS256, key)
                            .setExpiration(new Date(System.currentTimeMillis() + ms))
                            .compact();
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(key).parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            // 유효하지 않은 토큰
            return false;
        }
    }

    public String getUserIdFromToken(String token) {
        Claims claims = Jwts.parser()
                                    .setSigningKey(key)
                                    .parseClaimsJws(token)
                                    .getBody();
        return claims.getSubject();
    }


    public String resolveToken(HttpServletRequest request) {
        return request.getHeader("Authorization");
    }

    public Authentication getAuthentication(String token) {

        return null;
    }
}