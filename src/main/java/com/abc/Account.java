package com.abc;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Account {

	public static final int CHECKING = 0;
	public static final int SAVINGS = 1;
	public static final int MAXI_SAVINGS = 2;

	private final int accountType;
	public List<Transaction> transactions;

	public Account(int accountType) {
		this.accountType = accountType;
		this.transactions = new ArrayList<Transaction>();
	}

	public void deposit(double amount) {
		if (amount <= 0) {
			throw new IllegalArgumentException("amount must be greater than zero");
		} else {
			transactions.add(new Transaction(amount));
		}
	}

	public void withdraw(double amount) {
		if (amount <= 0) {
			throw new IllegalArgumentException("amount must be greater than zero");
		} else {
			transactions.add(new Transaction(-amount));
		}
	}

	
	public double interestEarned() {
		double amount = sumTransactions();
		switch (accountType) {
		case SAVINGS:
			if (amount <= 1000)
				return amount * 0.001;
			else
				return 1 + (amount - 1000) * 0.002;
			// case SUPER_SAVINGS:
			// if (amount <= 4000)
			// return 20;
		case MAXI_SAVINGS:
			/*
			 * if (amount <= 1000) return amount * 0.02; if (amount <= 2000)
			 * return 20 + (amount - 1000) * 0.05; return 70 + (amount - 2000) *
			 * 0.1;
			 */
			/*
			 * change Maxi-Saving accounts to have an interest rate of 5%
			 * assuming no withdrawls in the past 10 days otherwise 0.1%
			 */
			Date mostRecentWithdrawl = null;
			Date mostRecentDeposit = null;
			for (int i = 0; i < transactions.size(); i++) {
				if (transactions.get(i).amount < 0)
					mostRecentWithdrawl = transactions.get(i).getTransactionDate();
			}
			for (int i = 0; i < transactions.size(); i++) {
				if (transactions.get(i).amount > 0)
					mostRecentDeposit = transactions.get(i).getTransactionDate();
			}
			Date currentDate = DateProvider.getInstance().now();

			//if no deposits, return 0
			if (mostRecentDeposit == null) {
				return 0;
			} 
			//if there are withdrawls
			else if (mostRecentWithdrawl!=null){
				// Compare the dates
				// If there are no withdrawls in the past 10 days, it has an interest rate of 5%
				if (currentDate.getDate() - mostRecentWithdrawl.getDate() > 10) {
					return amount * 0.05;
				}
				// If there are withdrawls in the past 10 days, it has an interest rate of 0.1%
				else{
					return amount * 0.001;
				}
			//if no withdrawls
			}else if (mostRecentWithdrawl==null) {
				return amount * 0.05;
			}
		default:
			return amount * 0.001;
		}
	}

	public double sumTransactions() {
		double amount = 0.0;
		for (Transaction t : transactions)
			amount += t.amount;
		return amount;
	}
	/*
	private double checkIfTransactionsExist(boolean checkAll) {
		double amount = 0.0;
		for (Transaction t : transactions)
			amount += t.amount;
		return amount;
	}
	*/

	public int getAccountType() {
		return accountType;
	}
	
	//add the funtionality of calculating the daily interest
	public double dailyInterest(){
		return interestEarned()/365; //assuming there is 365 days per year
	}

	// transfer btw accounts (transferIn)
	public void transferIn(double amount) {
		if (amount <= 0) {
			throw new IllegalArgumentException("amount must be greater than zero");
		} else {
			transactions.add(new Transaction(amount));
		}
	}

	// transfer btw accounts (transferOut)
	public void transferOut(double amount) {
		if (amount <= 0) {
			throw new IllegalArgumentException("amount must be greater than zero");
		} else {
			transactions.add(new Transaction(-amount));
		}
	}

}
