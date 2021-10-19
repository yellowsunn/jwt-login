package com.yellowsunn.jwttoken.controller;

import com.yellowsunn.jwttoken.JwtTokenProvider;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.SignatureException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;

@RequiredArgsConstructor
@Controller
public class HelloController {

    private final JwtTokenProvider jwtTokenProvider;

    @ResponseBody
    @GetMapping("/")
    public String index(HttpServletResponse response) {
        response.setHeader("WWW-Authenticate", "Bearer " + jwtTokenProvider.makeJwtToken());
        try {
            Claims result = jwtTokenProvider.parseJwtToken("Bearer " + jwtTokenProvider.makeJwtToken());
            return result.get("email", String.class);
        } catch (SignatureException e) {
            return "실패";
        }
    }
}
