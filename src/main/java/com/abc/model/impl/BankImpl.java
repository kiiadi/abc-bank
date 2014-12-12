package com.abc.model.impl;

import java.util.List;

import com.abc.model.Bank;
import com.abc.model.Customer;

public class BankImpl implements Bank {
	private List<Customer> accounts;
	private long uid;
	
	
	public BankImpl(List<Customer> accounts, long nextLong) {
		this.accounts = accounts;
		this.uid = nextLong;
	}

	public long getUid() {
		return uid;
	}

	public List<Customer> getCustomers() {
		return accounts;
	}

}
