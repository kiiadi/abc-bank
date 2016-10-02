package com.abc;

import java.util.Calendar;
import java.util.Date;

public class Transaction {
	
    private final double amount;

    private final Date transactionDate;

    public Transaction(double amount) {
        
    	this(amount, DateProvider.getInstance().now());
    }
    
    public Transaction(double amount, Date transactionDate) {
    	
    	this.amount = amount;
    	this.transactionDate = transactionDate;
    }

	public double getAmount() {
		return amount;
	}

	public Date getTransactionDate() {
		return transactionDate;
	}

    
}
