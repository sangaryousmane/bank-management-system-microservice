package com.ousmane.customerservice.external;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Account {

    private Integer accountId;
    private Double accountBalance;
    private Integer customerId;

    private AccountType accountType;
    private String branchCode;
    private String bankName;

}

enum AccountType {
    DEBIT, CREDIT, SAVING, CURRENT
}
