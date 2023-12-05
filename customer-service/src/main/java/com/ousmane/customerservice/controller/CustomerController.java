package com.ousmane.customerservice.controller;
import com.ousmane.customerservice.entities.Customer;
import com.ousmane.customerservice.service.CustomerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/customers")
public class CustomerController {

    private final CustomerService customerService;


    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/")
    public ResponseEntity<Iterable<Customer>> getCustomers() {
        return ResponseEntity.ok(customerService.getAllCustomers());
    }

    @GetMapping("/{customerId}")
    public ResponseEntity<Customer> getCustomerById(
            @PathVariable("customerId") Integer customerId) {
        return ResponseEntity.ok(customerService.getCustomerById(customerId));
    }

    @PostMapping("/saveCustomer")
    public ResponseEntity<Customer> getCustomerById(
            @RequestBody Customer customer) {
        return ResponseEntity.ok(customerService.saveCustomer(customer));
    }

    @GetMapping("/account/{customerId}")
    public ResponseEntity<Customer> getCustomerByAccountId(
            @PathVariable("customerId") Integer customerId) {
        return ResponseEntity.ok(customerService.findCustomerByAccountId(customerId));
    }

    @PostMapping("/updateCustomer")
    public ResponseEntity<Customer> updateCustomerDetails(
            @RequestBody Customer customer,
            @RequestParam(value = "customerId") Integer customerId) {
        return ResponseEntity.ok(
                customerService.updateCustomer(customer, customerId));
    }

    @DeleteMapping("/deleteCustomer/{customerId}")
    public ResponseEntity<Map<String, Boolean>> deleteCustomerById(
            @PathVariable("customerId") Integer customerId) {
        boolean isDeleted = customerService.deleteCustomer(customerId);
        Map<String, Boolean> customerDelete = new HashMap<>();
        customerDelete.put("Deleted", isDeleted);
        return ResponseEntity.ok(customerDelete);
    }
}
