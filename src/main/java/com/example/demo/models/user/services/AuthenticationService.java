package com.example.demo.models.user.services;

import com.example.demo.config.TokenService;
import com.example.demo.models.dtos.LoginRequest;
import com.example.demo.models.dtos.LoginResponse;

import com.example.demo.models.user.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {

    private final UserRepository userRepository;

    private final TokenService tokenService;

    public AuthenticationService(
            UserRepository userRepository,
            PasswordEncoder passwordEncoder,
            TokenService tokenService) {
        this.userRepository = userRepository;
        this.tokenService = tokenService;
    }

    public LoginResponse execute(LoginRequest loginRequest) {
        var user = userRepository.findByUsername(loginRequest.getUsername());

        return tokenService.generateToken(loginRequest.getUsername(), user.get().getId());
    }
}
