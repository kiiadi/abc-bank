package com.abc;

import java.util.Date;

public class Transaction {
    private final double amount;

    private Date transactionDate;

    public Transaction(double amount, Date transactionDate) {
        this.amount = amount;
        this.transactionDate = transactionDate;
    }

    public double getAmount() {
        return amount;
    }

    public Date getDate() {
        return transactionDate;
    }
}
