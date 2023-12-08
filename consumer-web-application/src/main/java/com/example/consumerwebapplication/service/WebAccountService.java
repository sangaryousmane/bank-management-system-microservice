package com.example.consumerwebapplication.service;

import com.example.consumerwebapplication.exceptions.CurrentAccountNotFoundException;
import com.example.consumerwebapplication.external.Account;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class WebAccountService {

    private final RestTemplate restTemplate;


    protected String serviceURL = "http://ACCOUNT-SERVICE";

    public WebAccountService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public Account getAccountByNumber(Integer accountId){
        Account account=restTemplate.getForObject(
                serviceURL+"/api/v1/accounts/{accountId}", Account.class, accountId);

        if (account == null)
            throw new CurrentAccountNotFoundException("Account not found");
        else
            return account;
    }

    public List<Account> getAllAccounts(){
      return restTemplate.getForObject(serviceURL+"/api/v1/accounts/", List.class);
    }
}
