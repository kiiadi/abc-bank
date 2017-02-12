package com.abc;

import java.util.Date;

public class Transaction {
    public final double amount;

    private Date transactionDate;

    public Transaction(double amount) {
        this.amount = amount;
        this.transactionDate = new Date();
    }

    public Date getTransactionDate() {
        return transactionDate;
    }
}
