package com.clone.springbootredditbackend.service;

import com.clone.springbootredditbackend.Exception.SpringRedditException;
import com.clone.springbootredditbackend.domain.*;
import com.clone.springbootredditbackend.security.JWTProvider;
import com.clone.springbootredditbackend.web.dto.AuthenticationResponse;
import com.clone.springbootredditbackend.web.dto.LoginRequest;
import com.clone.springbootredditbackend.web.dto.RegisterRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

import static com.clone.springbootredditbackend.util.Constants.ACTIVATION_EMAIL;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final VerificationTokenRespository verificationTokenRespository;
    private final MailContentBuilder mailContentBuilder;
    private final MailService mailService;

    private final AuthenticationManager authenticationManager;
    private final JWTProvider jwtProvider;

    @Transactional
    public void signup(RegisterRequest registerRequest) {

        User user = User.builder()
                .username(registerRequest.getUsername())
                .password(encodePassword(registerRequest.getPassword()))
                .email(registerRequest.getEmail())
                .created(Instant.now())
                .enabled(false)
                .build();

        userRepository.save(user);

        String token = generateVerificationToken(user);
        String message = mailContentBuilder.build("Thank you for signing up to Springboot Reddit," +
                "please click on the below url to activate your account : " +
                ACTIVATION_EMAIL + "/" + token);

        mailService.sendMail(new NotificationEmail("Please Activate your account ", user.getEmail(), message));
    }

    private String generateVerificationToken(User user) {
        String token = UUID.randomUUID().toString();
        VerificationToken verificationToken = VerificationToken.builder()
                .token(token)
                .user(user)
                .build();

        verificationTokenRespository.save(verificationToken);

        return token;
    }

    private String encodePassword(String password) {
        return passwordEncoder.encode(password);
    }

    public void verifyAccount(String token) {
        Optional<VerificationToken> verificationTokenOptional =
                verificationTokenRespository.findByToken(token);
        verificationTokenOptional.orElseThrow(() -> new SpringRedditException("Invalid Token"));
        fetchUserAndEnable(verificationTokenOptional.get());
    }


    private void fetchUserAndEnable(VerificationToken verificationToken) {
        String username = verificationToken.getUser().getUsername();
        User user = userRepository.findByUsername(username).orElseThrow(() -> new
                SpringRedditException("User Not Found with id - " + username));
        user.updateEnabled(true);
        userRepository.save(user);
    }

    public AuthenticationResponse login(LoginRequest loginRequest) {
        Authentication authenticate =
                authenticationManager.authenticate(
                        new UsernamePasswordAuthenticationToken(loginRequest.getUsername(),
                                loginRequest.getPassword())
                );

        SecurityContextHolder.getContext().setAuthentication(authenticate);
        String authenticationToken = jwtProvider.generateToken(authenticate);
        return new AuthenticationResponse(authenticationToken, loginRequest.getUsername());
    }
}
