package com.ousmane.customerservice.external.consumer;

import com.ousmane.customerservice.exceptions.CustomerNotFoundException;
import com.ousmane.customerservice.external.Account;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.security.auth.login.AccountNotFoundException;
import java.util.List;


@CircuitBreaker(name = "external", fallbackMethod = "fallback")
@FeignClient(name = "account-service")
public interface AccountService {

    @GetMapping("/api/v1/accounts/customer/{customerId}")
    List<Account> getAccountList(@PathVariable Integer customerId);


    default void fallback(CustomerNotFoundException a){
        throw new CustomerNotFoundException(
                "customer service not accepting request",
                HttpStatus.NOT_FOUND);
    }
}
