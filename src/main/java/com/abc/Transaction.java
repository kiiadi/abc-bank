package com.abc;

import java.util.Date;

public class Transaction {
    public final double amount;

    private final Date transactionDate;

    public Transaction(double amount) {
        this.amount = amount;
        transactionDate = DateProvider.getInstance().now();
    }

}
