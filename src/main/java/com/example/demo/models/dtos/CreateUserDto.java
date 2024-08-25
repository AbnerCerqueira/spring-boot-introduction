package com.example.demo.models.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateUserDto {
    @Setter
    @NotBlank(message = "Preencha os campos vazios")
    @Pattern(regexp = "^[\\S]+$", message = "O username não pode conter espaços em branco")
    private String username;

    @Setter
    @NotBlank(message = "Preencha os campos vazios")
    @Pattern(regexp = "^[\\S]+$", message = "A senha não pode conter espaços em branco")
    private String password;

}
