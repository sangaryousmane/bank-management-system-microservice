package com.ousmane.customerservice.controller;
import com.ousmane.customerservice.entities.Customer;
import com.ousmane.customerservice.service.CustomerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.Map;

@Tag(name = "Restful APIs for managing customers",
        description = "GET, CREATE, UPDATE and DELETE customers information")
@Validated
@RestController
@RequestMapping("/api/v1/customers")
public class CustomerController {

    private final CustomerService customerService;


    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }


    @Operation(
            method = "GET",
            summary = "API for requesting all customers",
            description = "This is a GET request for retrieving all the customers in DB"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "HTTP status code for successfully retrieving customer details"
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
    public ResponseEntity<Iterable<Customer>> getCustomers() {
        return ResponseEntity.ok(customerService.getAllCustomers());
    }


    @Operation(
            method = "GET",
            summary = "API for requesting a single customer details",
            description = "This is a GET request for retrieving all the customers in DB"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "HTTP status code for successfully retrieving a customer details"
            ),
            @ApiResponse(
                    responseCode = "405",
                    description = "Invalid HTTP Status for Method Not Allowed",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponse.class)
                    )
            )
    })
    @GetMapping("/{customerId}")
    public ResponseEntity<Customer> getCustomerById(
            @PathVariable("customerId") Integer customerId) {
        return ResponseEntity.ok(customerService.getCustomerById(customerId));
    }


    @Operation(
            method = "POST",
            summary = "API for requesting creating new customer in DB",
            description = "This is a POST request saving customer details in DB"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "201",
                    description = "HTTP status code for successfully creating a customer details"
            ),
            @ApiResponse(
                    responseCode = "405",
                    description = "Invalid HTTP Status for Method Not Allowed",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponse.class)
                    )
            )
    })
    @PostMapping("/saveCustomer")
    public ResponseEntity<Customer> getCustomerById(
            @RequestBody Customer customer) {
        return ResponseEntity.ok(customerService.saveCustomer(customer));
    }


    @Operation(
            method = "GET",
            summary = "API for requesting all customers based on accounts",
            description = "This is a GET request for retrieving all the accounts associated with customers in DB"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "HTTP status code for successfully retrieving customer based on account details"
            ),
            @ApiResponse(
                    responseCode = "405",
                    description = "Invalid HTTP Status for Method Not Allowed",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponse.class)
                    )
            )
    })
    @GetMapping("/account/{customerId}")
    public ResponseEntity<Customer> getCustomerByAccountId(
            @PathVariable("customerId") Integer customerId) {
        return ResponseEntity.ok(customerService.findCustomerByAccountId(customerId));
    }



    @Operation(
            method = "PUT",
            summary = "API for updating customers details",
            description = "This is a PUT request for updating customers details"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "HTTP status code for successfully updating customers details"
            ),
            @ApiResponse(
                    responseCode = "405",
                    description = "Invalid HTTP Status for Method Not Allowed",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponse.class)
                    )
            )
    })
    @PutMapping("/updateCustomer")
    public ResponseEntity<Customer> updateCustomerDetails(
            @RequestBody Customer customer,
            @RequestParam(value = "customerId") Integer customerId) {
        return ResponseEntity.ok(
                customerService.updateCustomer(customer, customerId));
    }


    @Operation(
            method = "DELETE",
            summary = "API for deleting a customer details",
            description = "This is a GET request for deleting a customer"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "HTTP status code for successfully deleting a customer"
            ),
            @ApiResponse(
                    responseCode = "405",
                    description = "Invalid HTTP Status for Method Not Allowed",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponse.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Customer ID not found"
            )
    })
    @DeleteMapping("/deleteCustomer/{customerId}")
    public ResponseEntity<Map<String, Boolean>> deleteCustomerById(
            @PathVariable("customerId") Integer customerId) {
        boolean isDeleted = customerService.deleteCustomer(customerId);
        Map<String, Boolean> customerDelete = new HashMap<>();
        customerDelete.put("Deleted", isDeleted);
        return ResponseEntity.ok(customerDelete);
    }

    @Operation(hidden = true)
    @PutMapping("/withdraw/{accountId}")
    public ResponseEntity<String> withdrawFund(
            @RequestParam(value = "amount") Double amount,
            @PathVariable("accountId") Integer accountId) {
        return ResponseEntity.ok(
                customerService.withdraw(amount, accountId));
    }
}
