package com.abc.model.impl;

import com.abc.model.Account;
import com.abc.model.Statement;

public class StatementImpl implements Statement {

	private Account account;
	private double total;
	
	public StatementImpl(Account account, double total) {
		this.account = account;
		this.total = total;
	}

	public Account getAccount() {
		return account;
	}

	public double getTotal() {
		return total;
	}

}
