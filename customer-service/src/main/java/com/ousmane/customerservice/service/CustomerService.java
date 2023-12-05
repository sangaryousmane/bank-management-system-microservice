package com.ousmane.customerservice.service;

import com.ousmane.customerservice.entities.Customer;
import com.ousmane.customerservice.external.Account;
import com.ousmane.customerservice.external.consumer.AccountService;
import com.ousmane.customerservice.repo.CustomerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

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
        return customer;
    }

    public Customer getCustomerById(Integer customerId) {
        return customerRepository.findById(customerId)
                .orElseThrow(() -> new RuntimeException("customer not found"));
    }

    public Customer findCustomerByAccountId(Integer customerId) {
        Customer customer = customerRepository.findById(customerId).orElseThrow(() -> new RuntimeException("customer not found"));
        customer.setAccounts(accountService.getAccountList(customerId));
        return customer;
    }

    public boolean deleteCustomer(Integer customerId) {
        boolean isCustomerExist = customerRepository.existsById(customerId);

        if (isCustomerExist) {
            customerRepository.deleteById(customerId);
            return true;
        }
        return false;
    }
}
