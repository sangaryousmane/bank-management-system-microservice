package com.ousmane.accountservice;
import com.ousmane.accountservice.entities.Account;
import com.ousmane.accountservice.entities.AccountType;
import com.ousmane.accountservice.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

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
        Account account=Account.builder()
                .accountId(1).accountBalance(2231.01)
                .accountType(AccountType.SAVING).bankName("BOC")
                .customerId(12).branchCode("BOC123")
                .build();
         Account account1=Account.builder()
                .accountId(2).accountBalance(4001.01)
                 .accountType(AccountType.CREDIT)
                .bankName("ICBC").customerId(12).branchCode("ICBC123")
                .build();
         Account account2=Account.builder()
                .accountId(3).accountBalance(5011.11)
                .accountType(AccountType.SAVING).bankName("PSB")
                .customerId(13).branchCode("PSB123")
                .build();
         Account account3=Account.builder()
                .accountId(3).accountBalance(1001.11)
                .accountType(AccountType.DEBIT).bankName("PSB")
                .customerId(14).branchCode("PSB123")
                .build();
         accountRepo.saveAll(
                 List.of(account, account1, account2, account3));
    }
}
