package com.example.demo.api.controller;

import com.example.demo.domain.dto.CreateUserDto;
import com.example.demo.domain.dto.LoginRequest;
import com.example.demo.domain.dto.LoginResponse;
import com.example.demo.domain.user.User;
import com.example.demo.domain.user.UserService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/create")
    public ResponseEntity<User> createUser(@RequestBody CreateUserDto createUserDto) throws Exception {
        var newUser = userService.create(createUserDto);

        return new ResponseEntity<>(newUser, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> loginUser(@RequestBody LoginRequest loginRequest) {

        var result = userService.login(loginRequest);

        return ResponseEntity.ok(result);
    }

    @GetMapping("/private")
    public ResponseEntity<String> protectedEndPoint() {
        return ResponseEntity.ok("Rota protegida");
    }
}
