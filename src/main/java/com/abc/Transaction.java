package com.abc;

import java.util.Calendar;
import java.util.Date;

public class Transaction {

    /* Amount should be private to promote encapsulation */
    private final double amount;

    /* Should be unmodifiable as well */
    private final Date transactionDate;

    public Transaction(double amount) {
        this.amount = amount;
        this.transactionDate = DateProvider.getInstance().currentTime();
    }

    public double getAmount() {
	return this.amount;
    }

}
