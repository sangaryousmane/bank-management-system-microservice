package com.ousmane.accountservice.controller;

import com.ousmane.accountservice.service.AccountService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class AccountControllerTest {

    @MockBean
    AccountService accountService;



    @BeforeEach
    void setUp() {
        log.info("Set up before test");
    }

    @Test
    public void findAccountsByCustomerId(){
        assertNotEquals(0, accountService.findByCustomer(1000).size());
    }

    @AfterEach
    void tearDown() {
        log.info("All tests done..");
    }
}