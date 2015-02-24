package com.abc.banking.transaction;

import java.util.Calendar;
import java.util.Date;

/**
 * Transactions performed on a account
 *
 */
public class Transaction {

	private Date transactionDate;
	private double transactionAmount;
	private String transactionDescription;
	private TransactionType transactionType;

	public Transaction(double transactionAmount, String transactionDescription,TransactionType transactionType) {
		super();
		this.transactionAmount = transactionAmount;
		this.transactionDescription = transactionDescription;
		this.transactionType = transactionType;
		this.transactionDate = Calendar.getInstance().getTime();
	}

	public Date getTransactionDate() {
		return transactionDate;
	}

	public double getTransactionAmount() {
		return transactionAmount;
	}

	public String getTransactionDescription() {
		return transactionDescription;
	}

	public TransactionType getTransactionType() {
		return transactionType;
	}

}

