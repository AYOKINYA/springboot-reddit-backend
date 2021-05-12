package com.clone.springbootredditbackend.web.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class AdminRequest {

    private String username;
    private String adminSpell;

    @NotBlank
    private String refreshToken;
}
