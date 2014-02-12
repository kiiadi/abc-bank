package com.abc.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.abc.builder.AccountBuilder;
import com.abc.model.Account;
import com.abc.model.Transaction;

public class AccountController {
	@Autowired
	final AccountBuilder builder = null;
	final Map<Long, Account> accounts = new HashMap<Long, Account>();

	public Account get(long id) throws Exception {
		if (this.accounts.containsKey(id)) {
			return this.accounts.get(id);
		} else {
			throw new Exception("Account does not exists");
		}
	}

	public Account add(final String name, final int type) {
		final Account customer = builder.type(type).name(name).
				transactions(new ArrayList<Transaction>()).createAccount();
		this.accounts.put(customer.getUid(), customer);
		return customer;
	}

	public void delete(final long id) throws Exception {
		// may need to delete accounts also??
		if (this.accounts.containsKey(id)) {
			this.accounts.remove(id);
		} else {
			throw new Exception("Account already exists");
		}
	}
	
	public Collection<Account> getAll() {
		return this.accounts.values();
	}
	
	public void addTransaction(final long id, final Transaction trans) throws Exception {
		final Account account = this.accounts.get(id);
		if(account != null) {
			if(trans != null) {
				account.getTransactions().add(trans);
			} else {
				throw new Exception("Transaction cant be null");
			}
		} else {
			throw new Exception("Transaction not added");
		}
	}
	
	public void deleteTransaction(final long id, final Transaction trans) throws Exception {
		final Account account = this.accounts.get(id);
		if(account != null) {
			if(account.getTransactions().contains(trans)) {
				account.getTransactions().remove(trans);
			} else {
				throw new Exception("Transaction not valid with this Account");
			}
		} else {
			throw new Exception("Transaction not deleted");
		}
	}

	public boolean contains(long uid) {
		return this.accounts.containsKey(uid);
	}
}
