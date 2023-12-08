package com.ousmane.customerservice.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class CustomerExceptionHandler {


    @ExceptionHandler(value = CustomerNotFoundException.class)
    public ResponseEntity<ErrorHandler> customerHandler(
            CustomerNotFoundException ex){
        ErrorHandler errorHandler=ErrorHandler
                .builder()
                .message(ex.getMessage())
                .httpStatus(HttpStatus.NOT_FOUND)
                .build();
        return ResponseEntity.ok(errorHandler);
    }
}
