package com.ousmane.accountservice.service;

import com.ousmane.accountservice.entities.Account;
import com.ousmane.accountservice.exceptions.AccountNotFoundException;
import com.ousmane.accountservice.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

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
                .orElseThrow(() ->
                        new AccountNotFoundException("Account Not found"));
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
                .orElseThrow(() -> new AccountNotFoundException("Account found"));

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
    public List<Account> findByCustomer(Integer customerId) {
        return accountRepository.findByCustomerId(customerId);
    }

    @Override
    public Account updateBalance(Integer accountId, Double balance) {
        return accountRepository.findAccountByAccountBalance(balance, accountId);
    }
}
