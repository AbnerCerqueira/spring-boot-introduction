package com.example.demo.exceptions;

import com.example.demo.exceptions.custom.ConflictUserException;
import com.example.demo.exceptions.custom.UserNotFoundException;
import com.example.demo.exceptions.custom.WrongPasswordException;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.List;

@RestControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

    // 400
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        List<String> errorMessages = ex
                .getBindingResult()
                .getFieldErrors()
                .stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .toList();

        String errorMessage = errorMessages.getFirst();
        String description = String.format("Path: %s", request.getDescription(false));

        ErrorResponseDto responseDto = new ErrorResponseDto(errorMessage, description);

        return new ResponseEntity<>(responseDto, HttpStatus.BAD_REQUEST);
    }

    // 403
    @ExceptionHandler(WrongPasswordException.class)
    public final ResponseEntity<Object> handleWrongPasswordException(WrongPasswordException ex, WebRequest request) {
        String errorMessage = ex.getMessage();
        String description = String.format("Path: %s", request.getDescription(false));

        ErrorResponseDto responseDto = new ErrorResponseDto(errorMessage, description);

        return new ResponseEntity<>(responseDto, HttpStatus.FORBIDDEN);
    }

    // 404
    @ExceptionHandler(UserNotFoundException.class)
    public final ResponseEntity<Object> handleUserNotFoundException(UserNotFoundException ex, WebRequest request) {
        String errorMessage = ex.getMessage();
        String description = String.format("Path: %s", request.getDescription(false));

        ErrorResponseDto responseDto = new ErrorResponseDto(errorMessage, description);

        return new ResponseEntity<>(responseDto, HttpStatus.NOT_FOUND);
    }

    // 409
    @ExceptionHandler(ConflictUserException.class)
    public final ResponseEntity<Object> handleConflictUserException(ConflictUserException ex, WebRequest request) {
        String errorMessage = ex.getMessage();
        String description = String.format("Path: %s", request.getDescription(false));

        ErrorResponseDto responseDto = new ErrorResponseDto(errorMessage, description);

        return new ResponseEntity<>(responseDto, HttpStatus.CONFLICT);
    }

}
