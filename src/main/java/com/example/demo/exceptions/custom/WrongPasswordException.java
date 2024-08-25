package com.example.demo.exceptions.custom;

public class WrongPasswordException extends RuntimeException {
    public WrongPasswordException() {
        super("Senha errada");
    }
}
