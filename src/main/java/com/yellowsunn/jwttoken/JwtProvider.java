package com.yellowsunn.jwttoken;

import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.Date;
import java.util.Map;

@Component
public class JwtProvider {

    private final String SECRET_KEY;

    public JwtProvider(@Value("${jwt.secret}") String secret) {
        this.SECRET_KEY = secret;
    }

    public String makeToken(Map<String, String> payload) {
        Date now = new Date();

        JwtBuilder builder = Jwts.builder();

        builder.setHeaderParam(Header.TYPE, Header.JWT_TYPE)
                .setIssuer("yellowsunn.com")
                .setIssuedAt(now)
                .setExpiration(new Date(now.getTime() + Duration.ofMinutes(30).toMillis()))
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY);

        payload.forEach((key, value) -> builder.claim(key, value));

        return builder.compact();
    }

    public Claims parseToken(String token) {
        return Jwts.parser()
                .setSigningKey(SECRET_KEY)
                .parseClaimsJws(token)
                .getBody();
    }
}
