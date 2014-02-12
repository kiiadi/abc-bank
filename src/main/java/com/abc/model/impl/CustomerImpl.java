package com.abc.model.impl;

import java.util.List;

import com.abc.model.Account;
import com.abc.model.Customer;

public class CustomerImpl implements Customer {
	private List<Account> accounts;
	private long uid;
	private String name;
	
	public CustomerImpl(String name, List<Account> accounts, long nextLong) {
		this.accounts = accounts;
		this.uid = nextLong;
		this.name = name;
	}

	public long getUid() {
		return this.uid;
	}

	public List<Account> getAccounts() {
		return this.accounts;
	}
	
	public String getName() {
		return this.name;
	}


}
