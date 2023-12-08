package com.ousmane.customerservice.external.decoder;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ousmane.customerservice.exceptions.CustomerNotFoundException;
import feign.Response;
import feign.codec.ErrorDecoder;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;

import java.io.IOException;

@Log4j2
public class CustomerErrorDecoder implements ErrorDecoder {

    @Override
    public Exception decode(String s, Response response) {
        ObjectMapper mapper = new ObjectMapper();
        log.info("::{}", response.request().url());
        log.info("::{}", response.request().headers());

        try {
            ErrorResponse error = mapper.readValue(response.body()
                    .asInputStream(), ErrorResponse.class);
            return new CustomerNotFoundException(
                    error.getErrorMessage(),
                    error.getErrorCode());
        } catch (IOException ex) {
            throw new CustomerNotFoundException("customer or account not found: ",
                    HttpStatus.NOT_FOUND);
        }
    }
}
