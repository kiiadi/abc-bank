package com.abc;

import java.util.Date;

import com.abc.util.DefaultDateProvider;

public class Transaction {
    public final double amount;

    private Date transactionDate;

    public Transaction(double amount) {
        this.amount = amount;
        this.transactionDate = DefaultDateProvider.getInstance().now();
    }

}
