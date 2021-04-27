package com.clone.springbootredditbackend.domain;


import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.Instant;

@Getter
@NoArgsConstructor
@Entity
public class VerificationToken {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String token;

    @OneToOne(fetch = FetchType.LAZY)
    private User user;

    private Instant expiryDate;

    @Builder
    public VerificationToken(String token, User user) {
        this.token = token;
        this.user = user;
    }

    public void updateExpiryDate(Instant expiryDate) {
        this.expiryDate = expiryDate;
    }
}
