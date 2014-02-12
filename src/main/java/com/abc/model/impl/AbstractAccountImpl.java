package com.abc.model.impl;

import java.util.List;

import com.abc.model.Account;
import com.abc.model.Transaction;

public abstract class AbstractAccountImpl implements Account {

	private List<Transaction> trans;
	private long uid;
	private String name;
	
	public AbstractAccountImpl(List<Transaction> transactions, long uid, String name) {
		this.trans = transactions;
		this.uid = uid;
		this.name = name;
	}

	public long getUid() {
		return uid;
	}

	public List<Transaction> getTransactions() {
		return trans;
	}
	
	public String getName() {
		return this.name;
	}

}
