package com.example.demo.exceptions.custom;

public class ConflictUserException extends RuntimeException {
    public ConflictUserException() {
        super("Usuário já existe");
    }
}
