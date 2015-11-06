package com.abc;

import java.util.Date;

public class Transaction {
    public final double amount;

    public Date getTransactionDate() {
		return transactionDate;
	}

	public void setTransactionDate(Date transactionDate) {
		this.transactionDate = transactionDate;
	}

	public double getAmount() {
		return amount;
	}

	private Date transactionDate;

    public Transaction(double amount) {
        this.amount = amount;
        transactionDate = DateProvider.getInstance().now();
    }

}
