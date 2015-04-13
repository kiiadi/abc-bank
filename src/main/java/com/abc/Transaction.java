package com.abc;

import java.util.Date;

public class Transaction {

    public final double amount;
    public final Date transactionDate;

    public Transaction(double amount) {
    	this(amount, DateProvider.getInstance().now());
    }

    public Transaction(double amount, Date asOfDate) {
        this.amount = amount;
        this.transactionDate = asOfDate;
    }
}
