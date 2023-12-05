package com.ousmane.accountservice.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

@Entity(name = "Account")
@Table(name = "accounts")
@NoArgsConstructor
@AllArgsConstructor @Builder
public class Account implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    private Integer accountId;
    private Double accountBalance;
    private Integer customerId;
    @Enumerated(EnumType.STRING)
    private AccountType accountType;
    private String branchCode;
    private String bankName;

    public Integer getAccountId() {
        return accountId;
    }

    public void setAccountId(Integer accountId) {
        this.accountId = accountId;
    }

    public Double getAccountBalance() {
        return accountBalance;
    }

    public void setAccountBalance(Double accountBalance) {
        this.accountBalance = accountBalance;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public AccountType getAccountType() {
        return accountType;
    }

    public void setAccountType(AccountType accountType) {
        this.accountType = accountType;
    }

    public String getBranchCode() {
        return branchCode;
    }

    public void setBranchCode(String branchCode) {
        this.branchCode = branchCode;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    @Override
    public String toString() {
        return "Account{" +
                "accountId=" + accountId +
                ", accountBalance=" + accountBalance +
                ", customerId=" + customerId +
                ", accountType=" + accountType +
                ", branchCode='" + branchCode + '\'' +
                ", bankName='" + bankName + '\'' +
                '}';
    }
}
