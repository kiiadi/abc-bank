package com.abc.model.impl;

import com.abc.model.Transaction;

public abstract class AbstractTransactionImpl implements Transaction {

	private long uid;
	private double amount;
	private String date;
	
	public AbstractTransactionImpl(final double amount, final long uid, final String date) {
		this.date = date;
		this.amount = amount;
		this.uid = uid;
	}
	
	public long getUid() {
		return uid;
	}

	public double getAmount() {
		return this.amount;
	}

	public String getDateOfTransaction() {
		return date;
	}
	
}
