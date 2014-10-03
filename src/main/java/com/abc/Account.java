package com.abc;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public abstract class Account {
	
	public final static int DAYS_IN_YEAR = 365;

	protected List<Transaction> transactions;
	// storing account value will help performance if this is being requested very frequently. 
	// however the overhead in bookkeeping, and recalculating the value daily are costly. 
	//protected double accountValue;

	/**
	 * Get transaction history for this account.
	 * @return a list of all the transaction history
	 */
	public List<Transaction> getTransactions() {
		return transactions;
	}

	/**
	 * Construct a new account object. 
	 * keep the list thread safe.
	 */
	public Account() {
		this.transactions = Collections.synchronizedList(new ArrayList<Transaction>());
	}

	public void deposit(double amount) {
		if (amount <= 0) {
			throw new IllegalArgumentException("amount must be greater than zero");
		} else {
			transactions.add(new Deposit(amount));
		}
	}

	// checking for overdraft should be added here
	public void withdraw(double amount) {
		if (amount <= 0) {
			throw new IllegalArgumentException("amount must be greater than zero");
		} else {
			transactions.add(new Withdraw(amount));
		}
	}
	
	/**
	 * Transfer fund in or out of this account.
	 * @param amount: negative means transfer out, non-negative means transfer in.
	 */
	public void transfer(double amount) {
			transactions.add(new Transfer(amount));
	}

	/**
	 * Interest earned for one day, where interest should accrue daily (including. weekends)
	 * the implementation of this methods are different for each type of account.
	 * @param amount that accrues interest
	 * @return interest accrued per day
	 */
	public abstract double interestEarned(double amount);

	/**
	 * Total amount added to the account not counting interest
	 * @return
	 */
	public double sumTransactions() {
		double amount = 0.0;
		for (Transaction t: transactions)
			amount += t.amount;
		return amount;
	}
	
	public abstract String printName();

	/**
	 * Total amount in the account at requesting time
	 * @return
	 */
	public double calculateTotal() {
		
		if (transactions.size() == 0) {
			return 0.0;
		}
		
		Collections.sort(transactions, new Comparator<Transaction>() {
			public int compare(Transaction t1, Transaction t2) {
				return t1.getTransactionDate().compareTo(t2.getTransactionDate());
			}
		});
		
		Transaction previousTransaction = transactions.get(0);
		double amount = previousTransaction.amount;
		
		for (int i = 1; i < transactions.size(); i++) {
			Transaction tmp = previousTransaction; 
			previousTransaction = transactions.get(i);
			// days passed since the transaction before the previous transaction.
			int days = DateProvider.getInstance().getAgeInDays(tmp.getTransactionDate(), previousTransaction.getTransactionDate());
			// total interest accrued since that transaction till previous transaction.
			for (int j = 0; j < days; j++) {
				amount += interestEarned(amount);
			}
			amount += previousTransaction.amount;
		}
		
		int days = DateProvider.getInstance().getAgeInDays(previousTransaction.getTransactionDate(), DateProvider.getInstance().now());
		for (int j = 0; j < days; j++) {
			amount += interestEarned(amount);
		}
		return amount;
	}
	
	// this method is used for testing purpose.
	protected void addTransaction(Transaction t) {
		transactions.add(t);
	}
	
}
