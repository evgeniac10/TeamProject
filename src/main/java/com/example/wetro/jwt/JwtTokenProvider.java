package com.example.wetro.jwt;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class JwtTokenProvider implements InitializingBean {

    @Value("${jwt.secret}")
    private String secretKey;
    @Value("${jwt.tokentime}")
    private  Long tokentime;
    private Key key;
    private final Logger logger = LoggerFactory.getLogger(JwtTokenProvider.class);
    private static final String AUTHORITIES_KEY = "auth";


    public String createToken(Authentication authentication) {
        String authorities = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(","));

        long now = (new Date()).getTime();
        Date valid = new Date(now + this.tokentime);

        return Jwts.builder()
                .setSubject(authentication.getName())
                .claim(AUTHORITIES_KEY,authorities)
                .signWith(key,SignatureAlgorithm.HS512)
                .setExpiration(valid)
                .compact();

    }

    public Authentication getAuthentication(String token){
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();

        Collection<?extends GrantedAuthority> authorities = Arrays.stream(claims.get(AUTHORITIES_KEY)
                .toString()
                .split(","))
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());

        User principal = new User(claims.getSubject(),"",authorities);
        return new UsernamePasswordAuthenticationToken(principal,token,authorities);}

    public boolean validateToken(String token){
        try{Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token);
            return true;
        }catch(io.jsonwebtoken.security.SecurityException | MalformedJwtException e){
            logger.info("잘못된 JWT 서명입니다.");
        }catch(ExpiredJwtException e){
            logger.info("만료된 JWT 토큰 입니다.");
        }catch(UnsupportedJwtException e){
            logger.info("지원되지 않는 JWT 토큰입니다.");
        }catch(IllegalArgumentException e){
            logger.info("JWT 토큰이 잘못되었습니다.");
        }return false;
    }



    @Override
    public void afterPropertiesSet() throws Exception {
        byte[] keyByte = Decoders.BASE64.decode(secretKey);
        this.key = Keys.hmacShaKeyFor(keyByte);
        //HMAC은 충돌이 어려운 성질을 가집니다. 즉, 서로 다른 입력에 대해 동일한 해시 출력이 발생할 확률이 낮습니다.
    }
}