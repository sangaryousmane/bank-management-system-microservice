package com.ousmane.accountservice.repository;

import com.ousmane.accountservice.entities.Account;
import com.ousmane.accountservice.entities.AccountType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountRepository extends JpaRepository<Account, Integer> {

    @Query("SELECT a FROM Account a WHERE a.bankName=:bankName")
    List<Account> findByBankName(@Param(value = "bankName") String bankName);

    @Query("SELECT a FROM Account a WHERE a.accountType=:accountType")
    Account findByAccountType(@Param("accountType") AccountType accountType);

    @Query("SELECT a FROM Account a WHERE a.customerId=:customerId")
    List<Account> findByCustomerId(@Param("customerId") Integer customerId);

    @Query("SELECT a FROM Account a WHERE a.accountBalance=:balance")
    Account findAccountByAccountBalance(@Param("balance") Double balance);
}
