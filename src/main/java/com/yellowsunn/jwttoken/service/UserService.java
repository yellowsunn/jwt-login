package com.yellowsunn.jwttoken.service;

import com.yellowsunn.jwttoken.dto.RegisterDto;
import com.yellowsunn.jwttoken.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    @Transactional
    public void save(RegisterDto registerDto) {
        userRepository.save(registerDto.getUser());
    }
}
