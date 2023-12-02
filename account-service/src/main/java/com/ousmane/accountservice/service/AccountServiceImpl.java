package com.ousmane.accountservice.service;

import com.ousmane.accountservice.entities.Account;
import com.ousmane.accountservice.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;

    @Override
    public List<Account> getAllCurrentAccounts() {
        return accountRepository.findAll();
    }

    @Override
    public Account getAccountDetails(Integer accountId) {
        return accountRepository.findById(accountId)
                .orElseThrow(() -> new RuntimeException("Account Not found"));
    }

    @Override
    public Account createAccount(Account account) {
        return accountRepository.save(account);
    }

    @Override
    public boolean deleteAccount(Integer accountId) {
        boolean isAccountExist = accountRepository.existsById(accountId);
        if (isAccountExist) {
            accountRepository.deleteById(accountId);
            return true;
        }
        return false;
    }

    @Override
    public Account updateAccountDetails(Account account, Integer accountId) {
        Account accountInDB = accountRepository.findById(accountId)
                .orElseThrow(() -> new RuntimeException("Not found"));

        account.setBankName(accountInDB.getBankName());
        account.setBranchCode(accountInDB.getBranchCode());
        account.setAccountType(accountInDB.getAccountType());
        account.setCustomerId(accountInDB.getCustomerId());
        account.setAccountBalance(accountInDB.getAccountBalance());
        accountRepository.save(account);
        return account;
    }

    @Override
    public List<Account> findByBankName(String bankName) {
        return accountRepository.findByBankName(bankName);
    }

    @Override
    public List<Account> findByCustomerName(Integer customerId) {
        return accountRepository.findByCustomerId(customerId);
    }
}
