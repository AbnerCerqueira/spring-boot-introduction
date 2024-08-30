package com.example.demo.models.user.services;

import com.example.demo.exceptions.custom.ConflictUserException;
import com.example.demo.models.dtos.CreateUserDto;
import com.example.demo.models.user.User;
import com.example.demo.models.user.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class CreateUserService {

    private final PasswordEncoder passwordEncoder;

    private final UserRepository userRepository;

    public CreateUserService(PasswordEncoder passwordEncoder, UserRepository userRepository) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
    }

    public User execute(CreateUserDto createUserDto) {
        var isConflict = userRepository.findByUsername(createUserDto.getUsername());
        
        if (isConflict.isPresent()) {
            throw new ConflictUserException();
        }

        User newUser = new User();
        newUser.setUsername(createUserDto.getUsername());
        newUser.setPassword(passwordEncoder.encode(createUserDto.getPassword()));

        return userRepository.save(newUser);
    }
}
