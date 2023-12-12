package com.ousmane.customerservice;

import com.github.javafaker.Faker;
import com.ousmane.customerservice.entities.Customer;
import com.ousmane.customerservice.repo.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
@RequiredArgsConstructor
public class CustomerServiceApplication implements CommandLineRunner {

    private final CustomerRepository customerRepo;

    public static void main(String[] args) {
        SpringApplication.run(CustomerServiceApplication.class, args);
    }


    @Override
    public void run(String... args) throws Exception {
        Faker faker = new Faker();
        List<Customer> customers = new ArrayList<>();

        for (int i = 0; i < 150; i++) {
            Customer customer = Customer.builder()
                    .customerId(faker.number().numberBetween(1, 153))
                    .customerName(faker.name().fullName())
                    .customerPhone(faker.phoneNumber().cellPhone())
                    .city(faker.address().cityName())
                    .email(faker.internet().emailAddress()).build();
            customers.add(customer);
        }
        customerRepo.saveAll(customers);
    }
}
