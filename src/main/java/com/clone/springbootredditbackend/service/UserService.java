package com.clone.springbootredditbackend.service;


import com.clone.springbootredditbackend.domain.User;
import com.clone.springbootredditbackend.domain.UserRepository;
import com.clone.springbootredditbackend.web.dto.UserDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Slf4j
@Transactional
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public UserDto searchUser(String name) {
        Optional<User> user = userRepository.findByUsername(name);
        if (!user.isPresent())
            return null;
        return new UserDto(user.get());
    }
}
