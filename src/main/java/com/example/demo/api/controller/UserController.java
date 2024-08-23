package com.example.demo.api.controller;

import com.example.demo.models.dtos.LoginRequest;
import com.example.demo.models.dtos.LoginResponse;
import com.example.demo.models.user.User;

import com.example.demo.models.user.services.CreateUserService;
import com.example.demo.models.user.services.AuthenticationService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class UserController {

    private final CreateUserService createUserService;

    private final AuthenticationService authenticationService;

    public UserController(CreateUserService createUserService,
                          AuthenticationService authenticationService
    ) {
        this.createUserService = createUserService;
        this.authenticationService = authenticationService;
    }

    @PostMapping("/create")
    public ResponseEntity<User> createUser(@Valid @RequestBody User createUserDto) throws Exception {
        var newUser = createUserService.execute(createUserDto);

        return new ResponseEntity<>(newUser, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> loginUser(@Valid @RequestBody LoginRequest loginRequest) {

        var result = authenticationService.execute(loginRequest);

        return ResponseEntity.ok(result);
    }

    @GetMapping("/private")
    public ResponseEntity<String> protectedEndPoint() {
        return ResponseEntity.ok("Autenticato com sucesso");
    }
}
