package com.ousmane.accountservice;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
@AllArgsConstructor
public class AccountNotFoundException extends RuntimeException{

    private String message;
    private HttpStatus errorCode;
    public AccountNotFoundException() {
        super();
    }

    public AccountNotFoundException(String message) {
        super(message);
    }

}
