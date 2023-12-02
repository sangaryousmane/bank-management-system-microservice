package com.example.consumerwebapplication.controller;

import com.example.consumerwebapplication.external.Account;
import com.example.consumerwebapplication.service.WebAccountService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ConsumerController {

    private final WebAccountService webAccountService;

    public ConsumerController(WebAccountService webAccountService) {
        this.webAccountService = webAccountService;
    }

    @GetMapping("/{accountId}")
    public Account homePage(
            @PathVariable("accountId") Integer accountId) {
        return webAccountService.getAccountByNumber(accountId);
    }

    @GetMapping("/allAccounts")
    public ResponseEntity<List<Account>> accountsPage() {
        return ResponseEntity.ok(webAccountService.getAllAccounts());
    }
}
