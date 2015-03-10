package com.abc;

import java.math.BigDecimal;
import java.util.Date;

/*
 * 
 * Alex Lerner updates ( AlecLerner@gmail.com
 * 
 */


public class Transaction {
    // public final double amount;
	private final BigDecimal amount;

    private Date transactionDate;

//    public Transaction(double amount) {
//        this.amount = amount;
//        this.transactionDate = DateProvider.getInstance().now();
//    }

    public Transaction(double amount) {
        this.amount = new BigDecimal(amount);
        this.transactionDate = DateProvider.getInstance().now();
    }

	public BigDecimal getAmount() {
		return amount;
	}

}
