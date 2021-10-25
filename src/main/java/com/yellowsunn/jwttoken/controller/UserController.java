package com.yellowsunn.jwttoken.controller;

import com.yellowsunn.jwttoken.dto.LoginRequestDto;
import com.yellowsunn.jwttoken.dto.RegisterDto;
import com.yellowsunn.jwttoken.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletResponse;

@Slf4j
@Controller
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/register")
    public String registerForm(Model model) {
        model.addAttribute("user", new RegisterDto());
        return "register";
    }

    @PostMapping("/register")
    public String register(@Validated @ModelAttribute("user") RegisterDto registerDto,
                           BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            log.info("errors={}", bindingResult);
            return "/register";
        }

        try {
            userService.save(registerDto);
        } catch (DataIntegrityViolationException e) {
            bindingResult.rejectValue("username", "invalid", "사용할 수 없는 아이디입니다.");
            log.info("errors={}", bindingResult);
            return "/register";
        }

        return "redirect:/";
    }

    @GetMapping("/login")
    public String loginForm() {
        return "login";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute LoginRequestDto loginRequestDto, HttpServletResponse response) {
        return null;
    }
}
