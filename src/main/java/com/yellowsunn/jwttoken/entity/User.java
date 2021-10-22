package com.yellowsunn.jwttoken.entity;

import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
public class User {

    @Id @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String username;

    private String email;
    private String password;
}
