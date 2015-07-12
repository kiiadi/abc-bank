package com.abc;

import java.util.Calendar;
import java.util.Date;

public class Transaction {
    private final double amount;

    private Date transactionDate;


    private TransactionType transactionType;


    public Transaction(double amount, TransactionType transactionType) {
        this.amount = amount;
        this.transactionDate = DateProvider.getInstance().now();
        this.transactionType = transactionType;
    }

    public double getAmount() {
        return amount;
    }

    public TransactionType getTransactionType() {
        return transactionType;
    }
}
