package com.ousmane.customerservice.service;

import com.ousmane.customerservice.entities.Customer;
import com.ousmane.customerservice.external.consumer.AccountService;
import com.ousmane.customerservice.repo.CustomerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class CustomerService {

    private final AccountService accountService;

    private final CustomerRepository customerRepository;

    public Iterable<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    public Customer saveCustomer(Customer customer) {
        log.info("Saving customer details:: {}", customer);
        return customerRepository.save(customer);
    }

    public Customer updateCustomer(
            Customer customer, Integer customerId) {
        Customer customerInDB = customerRepository.findById(customerId)
                .orElseThrow(() -> new RuntimeException("customer not found"));

        customerInDB.setCustomerId(customer.getCustomerId());
        customerInDB.setCustomerName(customer.getCustomerName());
        customerInDB.setCustomerPhone(customer.getCustomerPhone());
        customerInDB.setCity(customer.getCity());
        customerInDB.setEmail(customer.getEmail());
        customerRepository.save(customerInDB);
        log.info("Updating customer details:: {}", customer);
        return customer;
    }

    public Customer getCustomerById(Integer customerId) {
        log.info("Retrieving customer details by ID: {}", customerId);
        return customerRepository.findById(customerId)
                .orElseThrow(() -> new RuntimeException("customer not found"));
    }

    public Customer findCustomerByAccountId(Integer customerId) {
        Customer customer = customerRepository.findById(customerId).orElseThrow(() -> new RuntimeException("customer not found"));
        customer.setAccounts(accountService.getAccountList(customerId));
        log.info("Retrieving customers and account details:: {}", customer.getAccounts());
        return customer;
    }

    public boolean deleteCustomer(Integer customerId) {
        boolean isCustomerExist = customerRepository.existsById(customerId);

        if (isCustomerExist) {
            customerRepository.deleteById(customerId);
            log.info("Deleting customers by ID: {}", customerId);
            return true;
        }
        log.error("OH OH! customer with ID {} can't be deleted", customerId);
        return false;
    }
}
