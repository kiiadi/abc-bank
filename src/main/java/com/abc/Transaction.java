package com.abc;

import org.joda.time.DateTime;

import com.abc.BankConstants.TransactionType;

public class Transaction {
	private final double amount;
	private final DateTime transactionDate;
	private final TransactionType transactionType;
	
	public Transaction(TransactionType transactionType, double amount) {
		this(transactionType, amount,DateProvider.now());
	}
	public Transaction(TransactionType transactionType, double amount , DateTime dateTime) {
		this.amount = amount;
		this.transactionDate = dateTime;
		this.transactionType = transactionType;
	}
	public double getAmount() {
		return amount;
	}

	public DateTime getTransactionDate() {
		return transactionDate;
	}

	public TransactionType getTransactionType() {
		return transactionType;
	}
}
