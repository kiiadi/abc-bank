package com.abc;

import java.util.Date;

public class Transaction {
    enum Type {
        deposit,
        withdrawal
    }

    private final double amount;

    private final Date transactionDate;

    public Transaction(final double amount) {
        this.amount = amount;
        this.transactionDate = DateProvider.getInstance().now();
    }

    public double getAmount() {
        return amount;
    }

    public Type getTransactionType() {
        if (getAmount() < 0) return Type.withdrawal;
        return Type.deposit;
    }
    @SuppressWarnings("UnusedDeclaration")
    public Date getTransactionDate() {
        return transactionDate;
    }
}
