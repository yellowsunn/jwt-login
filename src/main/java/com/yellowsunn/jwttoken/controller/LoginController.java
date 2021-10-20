package com.yellowsunn.jwttoken.controller;

import com.yellowsunn.jwttoken.dto.LoginDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequiredArgsConstructor
public class LoginController {

    @GetMapping("/login")
    public String loginForm() {
        return "login";
    }

    @ResponseBody
    @PostMapping("/login")
    public String login(@ModelAttribute LoginDto loginDto) {
        return loginDto.toString();
    }
}
