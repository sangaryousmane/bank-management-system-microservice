package com.ousmane.customerservice.external.consumer;

import com.ousmane.customerservice.external.Account;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "account-service")
public interface AccountService {

    @GetMapping("/api/v1/accounts/customer/{customerId}")
    List<Account> getAccountList(@PathVariable Integer customerId);
}
