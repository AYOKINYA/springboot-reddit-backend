package com.clone.springbootredditbackend.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.time.Instant;

@Getter
@NoArgsConstructor
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long userId;

    @NotBlank(message = "Username is required")
    private String username;

    @NotBlank(message = "Password is required")
    private String password;

    @Email
    @NotEmpty(message = "Email is required")
    private String email;

    private Instant created;

    private boolean enabled;

    @Builder
    public User(String username, String password, String email, Instant created, Boolean enabled) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.created = created;
        this.enabled = enabled;
    }

    public void updateEnabled(boolean enabled) {
        this.enabled = enabled;
    }


}
