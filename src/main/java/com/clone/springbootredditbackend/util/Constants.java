package com.clone.springbootredditbackend.util;

import lombok.experimental.UtilityClass;

@UtilityClass
//marks the class as final
//generates a private no-arg constructor
//allows methods or fields to be static
public class Constants {
    public static final String ACTIVATION_EMAIL = "http://localhost:8080/api/auth/accountVerification";
}
