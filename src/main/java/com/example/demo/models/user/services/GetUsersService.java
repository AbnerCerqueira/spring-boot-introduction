package com.example.demo.models.user.services;

import com.example.demo.models.dtos.GetUsersDto;
import com.example.demo.models.user.User;
import com.example.demo.models.user.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GetUsersService {
    private final UserRepository userRepository;

    public GetUsersService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<GetUsersDto> execute() {
        List<User> users = userRepository.findAll();
        return users.stream()
                .map(user -> new GetUsersDto(user.getId(), user.getUsername()))
                .collect(Collectors.toList());
    }
}
