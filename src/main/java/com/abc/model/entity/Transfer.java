package com.abc.model.entity;

import java.math.BigDecimal;

/**
 * Created by alexandr koller on 01/11/2014.
 */
public class Transfer {

    private Account sourceAccount;
    private Account destinationAccount;
    private BigDecimal amount;

    private Transfer(Account sourceAccount, Account destinationAccount) {
        this.sourceAccount = sourceAccount;
        this.destinationAccount = destinationAccount;
    }

    public static Transfer CreateInternalTransfer(Customer customer, String sourceAccountName, String destinationAccountName) {

        Account sourceAccount = customer.getAccountByName(sourceAccountName);
        Account destinationAccount = customer.getAccountByName(destinationAccountName);

        if(sourceAccount == null) {
            throw new AccountNotFound(sourceAccountName);
        }

        if(destinationAccount == null) {
            throw new AccountNotFound(destinationAccountName);
        }

        return new Transfer(sourceAccount, destinationAccount);
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Account getSourceAccount() {
        return sourceAccount;
    }

    public Account getDestinationAccount() {
        return destinationAccount;
    }

    public static class AccountNotFound extends RuntimeException {
        public AccountNotFound(String message) {
            super(message);
        }
    }
}
