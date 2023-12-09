package com.ousmane.customerservice.exceptions;

public class FundInsufficientException extends RuntimeException{

    public FundInsufficientException() {
        super();
    }

    public FundInsufficientException(String message) {
        super(message);
    }
}
