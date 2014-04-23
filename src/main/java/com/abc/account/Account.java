package com.abc.account;

import java.math.BigDecimal;
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

	public synchronized void deposit(BigDecimal amount) {

		if (amount.compareTo(new BigDecimal("0.00")) <= 0) {
			throw new IllegalArgumentException(
					"amount must be greater than zero");
		}

		transactions.add(new Transaction(amount));

	}

	public synchronized void withdraw(BigDecimal amount) throws Exception {

		if (amount.compareTo(new BigDecimal("0.00")) <= 0) {
			throw new IllegalArgumentException(
					"amount must be greater than zero");
		}

		if (amount.compareTo(sumTransactions()) > 0) {
			throw new Exception("insufficient funds on withdrawal");
		}

		transactions.add(new Transaction(amount.negate()));

	}

	public abstract BigDecimal interestEarned();

	public BigDecimal sumTransactions() {

		BigDecimal total = new BigDecimal("0.00");

		for (Transaction t : transactions) {

			total = total.add(t.getAmount());

		}

		return total.setScale(2, BigDecimal.ROUND_CEILING);
	}

	public String getLabel() {
		return label;
	}

	public List<Transaction> getTransactions() {
		return transactions;
	}

}
