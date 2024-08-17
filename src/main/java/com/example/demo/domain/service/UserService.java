package com.example.demo.domain.service;

import com.example.demo.domain.dto.CreateUserDto;
import com.example.demo.domain.dto.LoginRequest;
import com.example.demo.domain.dto.LoginResponse;
import com.example.demo.domain.entity.User;
import com.example.demo.errors.UserConflictException;
import com.example.demo.domain.user.repository.UserRepository;
import org.apache.coyote.BadRequestException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final PasswordEncoder passwordEncoder;

    private final UserRepository userRepository;

    private final TokenService tokenService;

    public UserService(
            UserRepository userRepository,
            PasswordEncoder passwordEncoder,
            TokenService tokenService) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.tokenService = tokenService;
    }

    public User create(CreateUserDto createUserDto) throws Exception {
        if (createUserDto.username().trim().isEmpty() | createUserDto.password().trim().isEmpty()) {
            throw new BadRequestException("Empty fields!");
        }

        var isConflict = userRepository.findByUsername(createUserDto.username());

        if (isConflict.isPresent()) {
            throw new UserConflictException();
        }

        User newUser = new User();
        newUser.setUsername(createUserDto.username());
        newUser.setPassword(passwordEncoder.encode(createUserDto.password()));

        return userRepository.save(newUser);
    }

    public LoginResponse login(LoginRequest loginRequest) {
        var user = userRepository.findByUsername(loginRequest.username());
        return tokenService.generateToken(loginRequest, user.get().getId());
    }
}
