package com.example.demo.models.user.services;

import com.example.demo.models.user.User;
import com.example.demo.models.user.UserRepository;
import org.apache.coyote.BadRequestException;
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

    public User execute(User user) throws Exception {
        var isConflict = userRepository.findByUsername(user.getUsername());
        
        if (isConflict.isPresent()) {
            throw new BadRequestException("Username alrety exists");
        }

        User newUser = new User();
        newUser.setUsername(user.getUsername());
        newUser.setPassword(passwordEncoder.encode(user.getPassword()));

        return userRepository.save(newUser);
    }

}
