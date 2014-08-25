package com.abc;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

public class Account {

	public static final int CHECKING = 0;
	public static final int SAVINGS = 1;
	public static final int MAXI_SAVINGS = 2;

	private final int accountType;
	public List<Transaction> transactions;
	private double balance = 0;

	public Account(int accountType) {
		this.accountType = accountType;
		this.transactions = new ArrayList<Transaction>();
	}

	public void deposit(double amount) {

		if (amount <= 0) {
			throw new IllegalArgumentException(
					"amount must be greater than zero");
		} else {
			//Synchronized to make sure balance field reflects the deposits/withdrawals on same account by other threads.
			// Also to makes sure Deposit is not done on the account if withdrawal action is being performed on this account.
			
			synchronized (this) {
				balance = balance + amount;
				transactions.add(new Transaction(amount));
			}

		}
	}

	public void withdraw(double amount) {
		if (amount <= 0) {
			throw new IllegalArgumentException(
					"amount must be greater than zero");
		} else {

			//Synchronized to make sure balance field reflects the deposits/withdrawals on same account by other threads.
			// Also to makes sure Withdrawal is not done on the account if deposit action is being performed on this account.
			
			synchronized (this) {
				// Check if the account has enough funds to cover this
				// withdrawal.
				if (amount > balance) {
					throw new IllegalArgumentException(
							"Invalid Amount, Withdrawal Amount("+amount+") cannot be greater than current balance("+balance+"). ");
				}

				balance = balance - amount;
				transactions.add(new Transaction(-amount));
			}

		}
	}

	public double interestEarned() {
		double amount = sumTransactions();

		// Return 0 if total amount is negative as interest should not be paid
		// on negative balances.
		if (amount < 0) {
			return 0;
		}

		switch (accountType) {
		case SAVINGS:
			if (amount <= 1000) {
				return amount * 0.001;
			} else {
				return 1 + (amount - 1000) * 0.002;
			}
		// New logic added set rate as 5% if no withdrawals are posted in last 10 days, if not set it to 0.1%.
		case MAXI_SAVINGS:
			 return hasWithdrawalsInThisPeriod(10) ? amount * 0.001 : amount * 0.05 ;
			
		default:
			return amount * 0.001;
		}
	}

	public double sumTransactions() {
		return checkIfTransactionsExist(true);
	}

	private double checkIfTransactionsExist(boolean checkAll) {
		double amount = 0.0;
		for (Transaction t : transactions)
			amount += t.amount;
		return amount;
	}

	public int getAccountType() {
		return accountType;
	}

	public double getBalance() {
		return balance;
		
	}
	
	// Returns true if account has withdrawals  in last "durationInDays". 
	public boolean hasWithdrawalsInThisPeriod(int durationInDays){
		boolean hasWithdrawal=false;
	    Date fromDate=DateProvider.addDays(-durationInDays);
		
		for(Transaction t:transactions){
			
			if(t.amount<0  &&  t.getTransactionDate().after(fromDate)){
				hasWithdrawal=true;
			}
		}
		
		return hasWithdrawal;
	}

}
