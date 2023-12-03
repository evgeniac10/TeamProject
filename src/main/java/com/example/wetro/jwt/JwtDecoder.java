package com.example.wetro.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class JwtDecoder {

    @Value("${jwt.secret}")
    private String secretKey;

    public Claims decoder(String token){
        return Jwts.parser()
                .setSigningKey(secretKey) // 서버에서 사용한 시크릿 키
                .parseClaimsJws(token)
                .getBody();

    }
}
