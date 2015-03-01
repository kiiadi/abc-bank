package com.abc;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public abstract class Account {

    public final List<Transaction> transactions;

    public Account() {
        this.transactions = new ArrayList<Transaction>();
    }

    public void deposit(BigDecimal amount) {
        if (amount.signum() <= 0) {
            throw new IllegalArgumentException("amount must be greater than zero");
        } else {
            transactions.add(new Transaction(amount, false));
        }
    }

    public void withdraw(BigDecimal amount) {
        if (amount.signum() <= 0) {
            throw new IllegalArgumentException("amount must be greater than zero");
        } else {
            transactions.add(new Transaction(amount.negate(), true));
        }
    }


    public abstract BigDecimal interestEarned();

    public BigDecimal sumTransactions() {
        BigDecimal amount = new BigDecimal(0.0);
        for (Transaction t: transactions)
            amount = amount.add(t.amount);
        return amount;
    }

    public abstract String getAccountName();

}
