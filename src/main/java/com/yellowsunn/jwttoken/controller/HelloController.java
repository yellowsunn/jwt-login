package com.yellowsunn.jwttoken.controller;

import com.yellowsunn.jwttoken.JwtProvider;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@RequiredArgsConstructor
@Controller
public class HelloController {

    private final JwtProvider jwtProvider;

    @GetMapping("/")
    public ResponseEntity<String> index(HttpServletResponse response) {
        Map<String, String> payload = new HashMap<>();
        payload.put("id", "아이디");
        payload.put("email", "chohankook95@gmail.com");

        String token = jwtProvider.makeToken(payload);
        log.info(token);

        try {
            Claims result = jwtProvider.parseToken(token);
            return ResponseEntity.ok(result.toString());
        } catch (SecurityException | MalformedJwtException e) {
            return ResponseEntity.status(401).body("잘못된 JWT 서명입니다.");
        } catch (ExpiredJwtException e) {
            return ResponseEntity.status(401).body("만료된 JWT 토큰입니다.");
        } catch (UnsupportedJwtException e) {
            return ResponseEntity.status(401).body("지원되지 않은 JWT 토큰입니다.");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(401).body("JWT 토큰이 잘못되었습니다.");
        }
    }
}
