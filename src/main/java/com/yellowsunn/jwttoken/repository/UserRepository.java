package com.yellowsunn.jwttoken.repository;

import com.yellowsunn.jwttoken.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
