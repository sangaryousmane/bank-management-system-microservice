package com.ousmane.accountservice;

import com.github.javafaker.Faker;
import com.ousmane.accountservice.entities.Account;
import com.ousmane.accountservice.entities.AccountType;
import com.ousmane.accountservice.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
@EnableDiscoveryClient
@RequiredArgsConstructor
public class AccountServiceApplication implements CommandLineRunner {

    private final AccountRepository accountRepo;

    public static void main(String[] args) {
        SpringApplication.run(AccountServiceApplication.class, args);
    }


    @Override
    public void run(String... args) throws Exception {
        Faker faker = new Faker();
        List<Account> accounts = new ArrayList<>();

        for (int i = 0; i < 150; i++) {
            Account account = Account.builder()
                    .accountId(faker.number().numberBetween(1, 153))
                    .bankName(faker.finance().creditCard())
                    .accountBalance(faker.number().randomDouble(100, 300, 10000))
                    .customerId(faker.number().numberBetween(1, 153))
                    .branchCode(faker.commerce().promotionCode()).build();
            if (i % 2 == 0)
                account.setAccountType(AccountType.CREDIT);
            else if (i % 3 == 0)
                account.setAccountType(AccountType.SAVING);
            else
                account.setAccountType(AccountType.DEBIT);
            accounts.add(account);
        }
        accountRepo.saveAll(accounts);
    }
}
