package com.example.consumerwebapplication.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class CurrentAccountNotFoundException extends RuntimeException {
    public CurrentAccountNotFoundException(String message) {
    }
}
