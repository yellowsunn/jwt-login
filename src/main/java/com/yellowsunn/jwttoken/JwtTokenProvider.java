package com.yellowsunn.jwttoken;

import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.time.Duration;
import java.util.Date;

@Component
public class JwtTokenProvider {

    private final String SECRET_KEY;

    public JwtTokenProvider(@Value("${jwt.secret}") String secret) {
        this.SECRET_KEY = secret;
    }

    public String makeJwtToken() {
        Date now = new Date();

        return Jwts.builder()
                .setHeaderParam(Header.TYPE, Header.JWT_TYPE)
                .setIssuer("fresh")
                .setIssuedAt(now)
                .setExpiration(new Date(now.getTime() + Duration.ofMinutes(30).toMillis()))
                .claim("id", "아이디")
                .claim("email", "chohankook95@gmail.com")
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .compact();
    }

    public Claims parseJwtToken(String authorizationHeader) {
        validationAuthorizationHeader(authorizationHeader);
        String token = extractToken(authorizationHeader);

        return Jwts.parser()
                .setSigningKey(SECRET_KEY)
                .parseClaimsJws(token)
                .getBody();
    }

    private void validationAuthorizationHeader(String header) {
        if (!StringUtils.hasText(header) || !header.startsWith("Bearer ")) {
            throw new IllegalArgumentException();
        }
    }

    private String extractToken(String header) {
        return header.substring("Bearer ".length());
    }
}
