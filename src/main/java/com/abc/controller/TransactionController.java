package com.abc.controller;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.abc.builder.TransactionBuilder;
import com.abc.model.Transaction;

public class TransactionController {

	@Autowired
	final TransactionBuilder builder = null;
	final Map<Long, Transaction> transactions = new HashMap<Long, Transaction>();

	public Transaction get(long id) throws Exception {
		if (this.transactions.containsKey(id)) {
			return this.transactions.get(id);
		} else {
			throw new Exception("Transaction does not exists");
		}
	}

	public Transaction add(final double amount, final int type) {
		final Transaction trans = builder.amount(amount).type(type).createTransaction();
		this.transactions.put(trans.getUid(), trans);
		return trans;
	}

	public void delete(final long id) throws Exception {
		// may need to delete accounts also??
		if (this.transactions.containsKey(id)) {
			this.transactions.remove(id);
		} else {
			throw new Exception("Transaction already exists");
		}
	}

	public Collection<Transaction> getAll() {
		return this.transactions.values();
	}
	
	public boolean contains(long uid) {
		return this.transactions.containsKey(uid);
	}

}
