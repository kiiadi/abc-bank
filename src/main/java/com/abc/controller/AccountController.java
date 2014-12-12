package com.abc.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.abc.builder.AccountBuilder;
import com.abc.model.Account;
import com.abc.model.Transaction;

/**
 * A simple controller that performs CRUD operations on an Account
 * 
 * @author erieed
 */
public class AccountController {
	//Builder for building account information
	@Autowired
	final AccountBuilder builder = null;
	final Map<Long, Account> accounts = new HashMap<Long, Account>();

	/**
	 * Get the Account based off its id
	 * 
	 * @param id - uid of account
	 * @return
	 * @throws Exception
	 */
	public Account get(long id) throws Exception {
		if (this.accounts.containsKey(id)) {
			return this.accounts.get(id);
		} else {
			throw new Exception("Account does not exists");
		}
	}

	/**
	 * Creates and account
	 * 
	 * @param name - name of account
	 * @param type - type of account (Checking, Savings, Max Savings)
	 * @return
	 */
	public Account add(final String name, final int type) {
		final Account customer = builder.type(type).name(name).
				transactions(new ArrayList<Transaction>()).createAccount();
		this.accounts.put(customer.getUid(), customer);
		return customer;
	}

	/**
	 * Deletes the Account from the store
	 * 
	 * @param id - uid of account
	 * @throws Exception
	 */
	public void delete(final long id) throws Exception {
		// may need to delete accounts also??
		if (this.accounts.containsKey(id)) {
			this.accounts.remove(id);
		} else {
			throw new Exception("Account already exists");
		}
	}
	
	/**
	 * Get all Accounts in the application
	 * 
	 * @return
	 */
	public Collection<Account> getAll() {
		return this.accounts.values();
	}
	
	/**
	 * Helper method to add a Transaction to the Account
	 * 
	 * @param id - Account id
	 * @param trans - transaction to add (assumes that Transaction is already in TransactionController)
	 * @throws Exception
	 */
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
	
	/**
	 * Delete a Transaction from the Account
	 * 
	 * @param id - uid of account
	 * @param trans - transaction to delete
	 * @throws Exception
	 */
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

	/**
	 * Checks to see if Account is apart of this store
	 * 
	 * @param uid - id of account
	 * @return
	 */
	public boolean contains(long uid) {
		return this.accounts.containsKey(uid);
	}
}
