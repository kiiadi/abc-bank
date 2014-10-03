package com.abc;

import java.util.Collections;
import java.util.Comparator;

public class MaxiSavingsAccount extends Account {
	
	public static final int MAX_DAYS = 10;
	
	/*
	 * Maxi Saving Account have an interest rate of 5% assuming no withdrawals in the past 10 days otherwise 0.1%
	 */
	@Override
	@Deprecated
	public double interestEarned(double a)  {
		return 0.0;
	}
	
	protected double interestEarned(double amount, int days)  {
		if (days <= MAX_DAYS) {
			return amount * 0.001 / DAYS_IN_YEAR;
		} else {
			return amount * 0.05 / DAYS_IN_YEAR;
		}
	}
	
	/**
	 * Maxi Account's interest formula: an interest rate of 5% assuming no withdrawals in the past 10 days otherwise 0.1%
	 * 
	 */
	@Override	
	public double calculateTotal() {
		
		if (transactions.size() == 0) return 0.0;
		int numOfDaysSinceWithdraw = Integer.MAX_VALUE;
		
		Collections.sort(transactions, new Comparator<Transaction>() {
			public int compare(Transaction t1, Transaction t2) {
				return t1.getTransactionDate().compareTo(t2.getTransactionDate());
			}
		});
		
		Transaction previousTransaction = transactions.get(0);
		double total = previousTransaction.amount;
		if (previousTransaction instanceof Withdraw) numOfDaysSinceWithdraw = 0;
		
		// loop through all the transactions after the first one for calculating interest accrued daily.
		for (int i = 1; i < transactions.size(); i++) {
			Transaction tmp = previousTransaction; 
			previousTransaction = transactions.get(i);
			// number of days between two transactions
			int days = DateProvider.getInstance().getAgeInDays(tmp.getTransactionDate(), previousTransaction.getTransactionDate());
			// accrue interest for the period using the previous amount
			for (int j = 0; j < days; j++) {
				total += interestEarned(total, numOfDaysSinceWithdraw);
				if (numOfDaysSinceWithdraw <= MAX_DAYS) {
					numOfDaysSinceWithdraw++;
				}
			}
			// add the amount for this transaction to the total
			total += previousTransaction.amount;
			// reset the counter if this transaction is a withdraw
			if (previousTransaction instanceof Withdraw) {
				numOfDaysSinceWithdraw = 0;
			}
		}
		
		// add the interest since the last transaction.
		int days = DateProvider.getInstance().getAgeInDays(previousTransaction.getTransactionDate(), DateProvider.getInstance().now());
		for (int j = 0; j < days; j++) {
			total += interestEarned(total, numOfDaysSinceWithdraw);
			if (numOfDaysSinceWithdraw <= MAX_DAYS) {
				numOfDaysSinceWithdraw++;
			}
		}
		return total;
	}

	@Override
	public String printName() {
		return "Maxi Savings Account\n";
	}
}
