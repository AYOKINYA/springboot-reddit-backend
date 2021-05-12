package com.clone.springbootredditbackend.service;

import com.clone.springbootredditbackend.domain.User;
import com.clone.springbootredditbackend.domain.UserRepository;
import com.clone.springbootredditbackend.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    @Transactional(readOnly = true)

    public UserDetails loadUserByUsername(String username) {
        Optional<User> userOptional = userRepository.findByUsername(username);
        User user = userOptional.orElseThrow(() -> new UsernameNotFoundException("No User " +
                "Found with username : " + username));

        return UserDetailsImpl.build(user);
    }

}
