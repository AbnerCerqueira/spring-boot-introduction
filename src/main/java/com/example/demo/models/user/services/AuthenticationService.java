package com.example.demo.models.user.services;

import com.example.demo.config.TokenService;
import com.example.demo.exceptions.custom.UserNotFoundException;
import com.example.demo.exceptions.custom.WrongPasswordException;
import com.example.demo.models.dtos.LoginRequest;
import com.example.demo.models.dtos.LoginResponse;

import com.example.demo.models.user.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {

    private final UserRepository userRepository;

    private final TokenService tokenService;

    private final PasswordEncoder passwordEncoder;

    public AuthenticationService(
            UserRepository userRepository,
            TokenService tokenService, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.tokenService = tokenService;
        this.passwordEncoder = passwordEncoder;
    }

    public LoginResponse execute(LoginRequest loginRequest) {
        var user = userRepository.findByUsername(loginRequest.username());

        if (user.isEmpty()) {
            throw new UserNotFoundException();
        }

        var isMatch = passwordEncoder.matches(loginRequest.password(), user.get().getPassword());
        if (!isMatch) {
            throw new WrongPasswordException();
        }

        return tokenService.generateToken(loginRequest.username(), user.get().getId());
    }
}
