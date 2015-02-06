package com.abc.domain;

import java.util.Date;

import com.abc.utils.DateProvider;

public class Transaction {

	private final double amount;
    private Date transactionDate;

    public Transaction(double amount) {
        this.amount = amount;
        this.transactionDate = DateProvider.now();
    }

	public double getAmount() {
		return amount;
	}
    
	public Date getTransactionDate() {
		return transactionDate;
	}
    public void setTransactionDate(Date transactionDate) {
		this.transactionDate = transactionDate;
	}

	@Override
	public String toString() {
		return "Transaction [amount=" + amount + ", transactionDate="
				+ transactionDate + "]";
	}
}
