package com.ousmane.customerservice.external.consumer;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "account-service")
public class AccountService {

    @GetMapping("/api/v1/accounts/customer/{customerId}")
}
