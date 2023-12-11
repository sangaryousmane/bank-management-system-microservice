package com.ousmane.accountservice.controller;

import com.ousmane.accountservice.entities.Account;
import com.ousmane.accountservice.service.AccountServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Tag(name = "Restful APIs for managing customers account",
        description = "GET, CREATE, UPDATE and DELETE customers bank account")
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/v1/accounts")
@Validated
public class AccountController {


    private final AccountServiceImpl accountService;


    @Operation(
            method = "GET",
            summary = "API for requesting all customers accounts",
            description = "This is a GET request for retrieving all the accounts in DB"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "HTTP status code for successfully retrieving account details"
            ),
            @ApiResponse(
                    responseCode = "405",
                    description = "Invalid HTTP Status for Method Not Allowed",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponse.class)
                    )
            )
    })
    @GetMapping("/")
    public Iterable<Account> getAllAccounts() {
        return accountService.getAllCurrentAccounts();
    }

    @GetMapping("/{accountId}")
    public Account getAccountDetails(
            @PathVariable(value = "accountId") Integer accountId) {
        return accountService.getAccountDetails(accountId);
    }


    @Operation(summary = "REST API to create bank account - POST request",
            description = "REST API to create open a customer account using account details")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "201",
                    description = "Account created successfully"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Internal server error",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponse.class))
            )
    })
    @PostMapping("/createAccount")
    public ResponseEntity<Account> createAccount(
            @RequestBody Account account) {
        return ResponseEntity.ok(accountService.createAccount(account));
    }

    @Operation(summary = "API for updating account details - PUT request",
            description = "This API uses PUT request based on the account ID to update" +
                    "customer's account detail")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "201",
                    description = "Account details successfully updated",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponse.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP status for Internal Server error",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponse.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "422",
                    description = "HTTP Status for Unprocessable Entity, validate your inputs well",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponse.class)
                    )
            )
    })
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
            @PathVariable("customerId") Integer customerId) {
        return ResponseEntity.ok(accountService.findByCustomer(customerId));
    }


    @Operation(hidden = true)
    @PutMapping("/accountBalance/{accountId}")
    public Account accountByBalance(
            @PathVariable("accountId") Integer accountId,
            @RequestParam("balance") Double balance) {
        return accountService.updateBalance(accountId, balance);
    }
}
