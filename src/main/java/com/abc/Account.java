package com.abc;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/*
 * 
 * Alex Lerner updates ( AlecLerner@gmail.com
 * 
 */

public abstract class Account {

	private List<Transaction> transactions;
	
	Account() {
		transactions = new ArrayList<Transaction>();
	}

	public void deposit(double amount) {
		if (amount <= 0) {
			throw new IllegalArgumentException("amount must be greater than zero");
		}
		getTransactions().add(new Transaction(amount));
	}

	public void withdraw(double amount) {
		if (amount <= 0) {
			throw new IllegalArgumentException("amount must be greater than zero");
		}
		getTransactions().add(new Transaction(-amount));
	}

	BigDecimal interestEarned() {
		BigDecimal amount = sumTransactions();
		return calculateInterest( amount );
	}
	
	abstract protected  BigDecimal calculateInterest(BigDecimal amount);

	public BigDecimal sumTransactions() {
		BigDecimal amount = new BigDecimal(0.0);
		for (Transaction t: getTransactions())
			amount = amount.add( t.getAmount());
		
		return amount;
	}
	
	abstract protected String getAccountDescription();

	public List<Transaction> getTransactions() {
		return transactions;
	}


}
