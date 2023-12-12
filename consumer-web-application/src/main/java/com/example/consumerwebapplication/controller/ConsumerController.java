package com.example.consumerwebapplication.controller;

import com.example.consumerwebapplication.external.Account;
import com.example.consumerwebapplication.service.WebAccountService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/consumerApp")
public class ConsumerController {

    private final WebAccountService webAccountService;

    public ConsumerController(WebAccountService webAccountService) {
        this.webAccountService = webAccountService;
    }

    @Operation(
            summary= "Retrieve customers account data from accountId microservice",
            description = "This API takes an account ID and display the " +
                    "corresponding customer account",
            method = "GET"
    )
    @ApiResponses({
            @ApiResponse(
                    description = "CUSTOMER ACCOUNT NOT FOUND",
                    responseCode = "404"
            ),
            @ApiResponse(
                    description = "CUSTOMERS ACCOUNT RETRIEVED SUCCESSFULLY",
                    responseCode = "200"
            )
    })
    @GetMapping("/{accountId}")
    public Account homePage(
            @PathVariable("accountId") Integer accountId) {
        return webAccountService.getAccountByNumber(accountId);
    }


    @Operation(
            summary = "Consume the account api and display all accounts",
            description = "Display all the customers accounts details",
            method = "GET"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "ACCOUNTS DETAILS RETRIVE SUCCESSFULLY",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponse.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "405",
                    description = "STATUS FOR METHOD NOT ALLOWED",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponse.class)
                    )
            )
    })
    @GetMapping("/allAccounts")
    public ResponseEntity<List<Account>> accountsPage() {
        return ResponseEntity.ok(webAccountService.getAllAccounts());
    }
}
