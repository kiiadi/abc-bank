package com.abc.account;

import java.util.ArrayList;
import java.util.List;

import com.abc.Transaction;

/**
 * Base class for concrete Account classes. Subclasses will override abstract
 * methods and provide concrete implementations.
 * 
 */
public abstract class Account {

	protected String label;

	private List<Transaction> transactions;

	public Account() {
		this.transactions = new ArrayList<Transaction>();
	}

	public synchronized void deposit(double amount) {

		if (amount <= 0) {
			throw new IllegalArgumentException(
					"amount must be greater than zero");
		}

		transactions.add(new Transaction(amount));

	}

	public synchronized void withdraw(double amount) throws Exception {

		if (amount <= 0) {
			throw new IllegalArgumentException(
					"amount must be greater than zero");
		}

		if (amount > sumTransactions()) {
			throw new Exception("insufficient funds on withdrawal");
		}

		transactions.add(new Transaction(-amount));

	}

	public abstract double interestEarned();

	public double balance() {
		return sumTransactions() + interestEarned();
	}

	public double sumTransactions() {

		double amount = 0.0;
		for (Transaction t : transactions)
			amount += t.getAmount();
		return amount;
	}

	public String getLabel() {
		return label;
	}

	public List<Transaction> getTransactions() {
		return transactions;
	}

}
