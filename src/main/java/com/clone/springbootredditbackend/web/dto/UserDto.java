package com.clone.springbootredditbackend.web.dto;

import com.clone.springbootredditbackend.domain.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

    private String username;
    private String email;

    public UserDto(User entity) {
        this.username = entity.getUsername();
        this.email = entity.getEmail();
    }

}
