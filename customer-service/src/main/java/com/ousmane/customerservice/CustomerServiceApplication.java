package com.ousmane.customerservice;

import com.ousmane.customerservice.entities.Customer;
import com.ousmane.customerservice.external.Account;
import com.ousmane.customerservice.external.consumer.AccountService;
import com.ousmane.customerservice.repo.CustomerRepository;
import com.ousmane.customerservice.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
@RequiredArgsConstructor
public class CustomerServiceApplication implements CommandLineRunner {

    private final CustomerRepository customerRepo;
    private final AccountService accountService;

    public static void main(String[] args) {
        SpringApplication.run(CustomerServiceApplication.class, args);
    }


    @Override
    public void run(String... args) throws Exception {
        Customer customer=Customer.builder()
                .customerId(12)
                .customerName("Ousmane Sangary")
                .customerPhone("13237080231")
                .city("Wuhan")
                .email("sangary@gmail.com").build();
        List<Account> accounts =
                accountService.getAccountList(customer.getCustomerId());
        customer.setAccounts(accounts);

        Customer customer1=Customer.builder()
                .customerId(13)
                .customerName("Peter Pratt")
                .customerPhone("122992332")
                .city("Beijing")
                .email("peter@gmail.com").build();
        List<Account> accounts1 =
                accountService.getAccountList(customer1.getCustomerId());
        customer1.setAccounts(accounts1);

        Customer customer2=Customer.builder()
                .customerId(14)
                .customerName("James Gaye")
                .customerPhone("14223334111")
                .city("Shanghai")
                .email("james@gmail.com").build();
        List<Account> accounts2 =
                accountService.getAccountList(customer2.getCustomerId());
        customer2.setAccounts(accounts2);
        customerRepo.saveAll(List.of(customer, customer1, customer2));
    }
}
