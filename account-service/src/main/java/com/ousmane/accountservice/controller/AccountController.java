package com.ousmane.accountservice.controller;

import com.ousmane.accountservice.entities.Account;
import com.ousmane.accountservice.service.AccountServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/accounts")
public class AccountController {


    private final AccountServiceImpl accountService;


    @GetMapping("/")
    public Iterable<Account> getAllAccounts() {
        return accountService.getAllCurrentAccounts();
    }

    @GetMapping("/{accountId}")
    public Account getAccountDetails(
            @PathVariable(value = "accountId") Integer accountId) {
        return accountService.getAccountDetails(accountId);
    }

    @PostMapping("/createAccount")
    public ResponseEntity<Account> createAccount(
            @RequestBody Account account) {
        return ResponseEntity.ok(accountService.createAccount(account));
    }

    @PutMapping("/updateAccount/{accountId}")
    public ResponseEntity<Account> updateAccount(
            @RequestBody Account account, @PathVariable Integer accountId) {
        return ResponseEntity.ok(accountService.updateAccountDetails(account, accountId));
    }


    @DeleteMapping("/deleteAccount/{accountId}")
    public ResponseEntity<Map<String, Boolean>> deleteAccount(@PathVariable Integer accountId) {
        boolean isAccountDeleted = accountService.deleteAccount(accountId);
        Map<String, Boolean> recordDetails = new HashMap<>();
        recordDetails.put("Deleted", isAccountDeleted);
        return ResponseEntity.ok(recordDetails);
    }

    @GetMapping("/customer/{customerId}")
    public ResponseEntity<List<Account>> accountByCustomer(
            @PathVariable("customerId") Integer customerId){
        return ResponseEntity.ok(accountService.findByCustomer(customerId));
    }

    @PutMapping("/accountBalance")
    public ResponseEntity<Account> accountByBalance(
            @RequestParam("balance") Double balance){
        return ResponseEntity.ok(accountService.updateBalance(balance));
    }
}
