package com.example.demo.service;

import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    PasswordEncoder passwordEncoder;

    UserRepository userRepository;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
    }

    public User create(User user) {
        User newUser = user;

        newUser.setPassword(passwordEncoder.encode(newUser.getPassword()));
        userRepository.save(newUser);
        return newUser;
    }

    public User login(User user) {
        User pUser = userRepository.findByUsername(user.getUsername());
        if (pUser == null) {
            return null;
        }

        boolean isMatch = passwordEncoder.matches(user.getPassword(), pUser.getPassword());

        if (!isMatch) {
            throw new RuntimeException("Wrong password");
        }

        return pUser;
    }

}
