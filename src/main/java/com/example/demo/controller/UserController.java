package com.example.demo.controller;

import com.example.demo.models.dtos.CreateUserDto;
import com.example.demo.models.dtos.GetUsersDto;
import com.example.demo.models.dtos.LoginRequest;
import com.example.demo.models.dtos.LoginResponse;
import com.example.demo.models.user.User;

import com.example.demo.models.user.services.CreateUserService;
import com.example.demo.models.user.services.AuthenticationService;
import com.example.demo.models.user.services.GetUsersService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class UserController {

    private final CreateUserService createUserService;

    private final AuthenticationService authenticationService;

    private final GetUsersService getUsersService;

    public UserController(
            CreateUserService createUserService,
            AuthenticationService authenticationService,
            GetUsersService getUsersService
    ) {
        this.createUserService = createUserService;
        this.authenticationService = authenticationService;
        this.getUsersService = getUsersService;
    }

    @PostMapping("/create")
    public ResponseEntity<User> createUser(@Valid @RequestBody CreateUserDto createUserDto) {
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
        return ResponseEntity.ok("Autenticado com sucesso");
    }

    @GetMapping("/users")
    public ResponseEntity<List<GetUsersDto>> getUsers() {
        var fodase = getUsersService.execute();
        return ResponseEntity.ok(fodase);
    }
}

