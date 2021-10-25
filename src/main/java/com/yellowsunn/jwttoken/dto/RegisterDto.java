package com.yellowsunn.jwttoken.dto;

import com.yellowsunn.jwttoken.entity.User;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class RegisterDto {

    @NotBlank
    @Size(min = 3, max = 20)
    private String username;

    @Email
    @NotBlank
    private String email;

    @NotBlank
    @Size(min = 3, max = 20)
    private String password;

    public User getUser() {
        return User.builder()
                .username(username)
                .email(email)
                .password(password)
                .build();
    }
}
