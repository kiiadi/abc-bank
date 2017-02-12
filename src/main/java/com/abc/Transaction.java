package com.abc;

import java.util.Date;

public class Transaction {

    private final double amount;

    private final Date transactionDate;

    public Transaction(double amount) {
        this.amount = amount;
        this.transactionDate = new Date();
    }

    public Date getTransactionDate() {
        return transactionDate;
    }

    public double getAmount() {
        return amount;
    }

}
