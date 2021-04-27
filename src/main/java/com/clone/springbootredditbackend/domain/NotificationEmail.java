package com.clone.springbootredditbackend.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class NotificationEmail {
    private String subject;
    private String recipient;
    private String body;
}
