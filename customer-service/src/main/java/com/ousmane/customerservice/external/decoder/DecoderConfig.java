package com.ousmane.customerservice.external.decoder;

import feign.codec.ErrorDecoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DecoderConfig {

    @Bean
    ErrorDecoder customDecoder(){
        return new CustomerErrorDecoder();
    }
}
