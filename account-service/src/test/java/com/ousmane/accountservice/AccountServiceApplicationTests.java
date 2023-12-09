package com.ousmane.accountservice;

import com.ousmane.accountservice.entities.Account;
import com.ousmane.accountservice.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@RequiredArgsConstructor
class AccountServiceApplicationTests {

    private final AccountRepository repo;
    @Test
    void contextLoads() {
    }

    @Test
    void findByAccountBalance(){
        Account account =
                repo.findAccountByAccountBalance(300.9);
        assertThat(account.getAccountId()).isGreaterThanOrEqualTo(4000);
        System.out.println("PASSED");
    }

}
