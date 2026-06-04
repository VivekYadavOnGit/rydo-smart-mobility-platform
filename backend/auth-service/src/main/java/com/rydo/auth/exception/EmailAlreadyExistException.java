package com.rydo.auth.exception;

import org.springframework.web.bind.annotation.RestControllerAdvice;


public class EmailAlreadyExistException extends RuntimeException {
    public EmailAlreadyExistException(String message) {

        super(message);
    }
}
