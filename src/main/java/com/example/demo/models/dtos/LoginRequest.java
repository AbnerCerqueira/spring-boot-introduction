package com.example.demo.models.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class LoginRequest {

    @NotNull(message = "Null fields")
    @NotEmpty(message = "Empty fields")
    @NotBlank(message = "Blank fields")
    @Pattern(regexp = "^[\\S]+$", message = "Username cannot contain spaces")
    private String username;

    @NotNull(message = "Null fields")
    @NotEmpty(message = "Empty fields")
    @NotBlank(message = "Blank fields")
    @Pattern(regexp = "^[\\S]+$", message = "Password cannot contain spaces")
    private String password;

}
