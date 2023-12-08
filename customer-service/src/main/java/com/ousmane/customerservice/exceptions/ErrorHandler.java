package com.ousmane.customerservice.exceptions;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
@AllArgsConstructor @Builder
public class ErrorHandler {

    private String message;
    private HttpStatus httpStatus;
}
