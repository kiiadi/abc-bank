package com.abc;

import java.util.ArrayList;
import java.util.List;

public abstract class Account implements AccountIntf{

	private final int accountType;
	public List<Transaction> transactions;

	public Account(int accountType) {
		this.accountType = accountType;
		this.transactions = new ArrayList<Transaction>();
	}

	public void deposit(double amount) {
		if (amount <= 0) {
			throw new IllegalArgumentException(
					"amount must be greater than zero");
		} else {
			transactions.add(new Transaction(amount));
		}
	}


	public void withdraw(double amount) {
		if (amount <= 0) {
			throw new IllegalArgumentException(
					"amount must be greater than zero");
		} else if(sumTransactions() < amount) { //Need to check the Balance
			throw new IllegalArgumentException(
					"Insufficient funds" );
		}
		else {
			transactions.add(new Transaction(-amount));
		}
	}

	public abstract double interestEarned() ;

	public double sumTransactions() {
		double amount = 0.0;
		for (Transaction t : transactions)
			amount += t.amount;
		return amount;
		// return checkIfTransactionsExist(true); Not Needed
	}

	/*
	 * private double checkIfTransactionsExist(boolean checkAll) { double amount
	 * = 0.0; for (Transaction t: transactions) amount += t.amount; return
	 * amount; }
	 */

	public int getAccountType() {
		return accountType;
	}

}
