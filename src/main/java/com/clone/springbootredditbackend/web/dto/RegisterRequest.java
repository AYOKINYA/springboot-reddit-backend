package com.clone.springbootredditbackend.web.dto;

import com.clone.springbootredditbackend.domain.User;
import lombok.AllArgsConstructor;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class RegisterRequest {

    private String username;
    private String email;
    private String password;

}
