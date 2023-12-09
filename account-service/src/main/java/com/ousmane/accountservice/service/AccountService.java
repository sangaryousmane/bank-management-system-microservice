package com.ousmane.accountservice.service;

import com.ousmane.accountservice.entities.Account;

import java.util.List;


public interface AccountService {

    List<Account> getAllCurrentAccounts();
    Account getAccountDetails(Integer accountId);
    Account createAccount(Account account);
    boolean deleteAccount(Integer accountId);
    Account updateAccountDetails(Account account, Integer accountId);
    List<Account> findByBankName(String bankName);
    List<Account> findByCustomer(Integer customerId);
    Account findByBalance(Double balance);
}
