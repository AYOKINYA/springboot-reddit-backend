package com.clone.springbootredditbackend.web.controller;

import com.clone.springbootredditbackend.service.UserService;
import com.clone.springbootredditbackend.web.dto.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/search")
    public ResponseEntity<UserDto> searchUser(@RequestParam("name") String name) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(userService.searchUser(name));
    }
}
