package com.ousmane.customerservice.external.decoder;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
@AllArgsConstructor
@Builder
public class ErrorResponse {

    private String errorMessage;
    private HttpStatus errorCode;
}
