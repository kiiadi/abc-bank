package com.abc.controller;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.abc.builder.TransactionBuilder;
import com.abc.model.Transaction;

/**
 * 
 * Simple controller for Transactions
 * 
 * @author erieed
 */
public class TransactionController {

	@Autowired
	final TransactionBuilder builder = null;
	final Map<Long, Transaction> transactions = new HashMap<Long, Transaction>();

	/**
	 * Get a Transaction from the store
	 * 
	 * @param id - uid of transaction
	 * @return
	 * @throws Exception
	 */
	public Transaction get(long id) throws Exception {
		if (this.transactions.containsKey(id)) {
			return this.transactions.get(id);
		} else {
			throw new Exception("Transaction does not exists");
		}
	}

	/**
	 * Add a transaction to the store
	 * 
	 * 
	 * @param amount - amount to add
	 * @param type - type of transaction (Interest, Withdraw, and Deposit)
	 * @return
	 */
	public Transaction add(final double amount, final int type) {
		final Transaction trans = builder.amount(amount).type(type).createTransaction();
		this.transactions.put(trans.getUid(), trans);
		return trans;
	}

	/**
	 * Delete Transaction from the store
	 * 
	 * @param id - uid of Transaction
	 * @throws Exception
	 */
	public void delete(final long id) throws Exception {
		// may need to delete accounts also??
		if (this.transactions.containsKey(id)) {
			this.transactions.remove(id);
		} else {
			throw new Exception("Transaction already exists");
		}
	}

	/**
	 * Get all Transactions in the store
	 * 
	 * @return
	 */
	public Collection<Transaction> getAll() {
		return this.transactions.values();
	}
	
	/**
	 * Check to see if the Transaction is in store
	 * 
	 * @param uid
	 * @return
	 */
	public boolean contains(long uid) {
		return this.transactions.containsKey(uid);
	}

}
