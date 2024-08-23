package com.example.demo.models.user;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("users")
public class User {

    @Id
    private String id;

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

    public User() {

    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
