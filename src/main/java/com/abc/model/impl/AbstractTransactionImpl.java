package com.abc.model.impl;

import java.util.Date;

import com.abc.model.Transaction;

public abstract class AbstractTransactionImpl implements Transaction {

	private long uid;
	private double amount;
	private Date date;
	
	public AbstractTransactionImpl(final double amount, final long uid, final Date date) {
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

	public Date getDateOfTransaction() {
		return date;
	}
	
}
