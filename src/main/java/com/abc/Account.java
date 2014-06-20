/************
 * Account.java
 * 
 * An account is a data structure that contains the account type 
 * as well as a list of Transaction objects.
 * 
 * @author Martin Aydin 
 */

package com.abc;

import java.util.ArrayList;
import java.util.List;

public class Account {

	// Constants. Use enum instead of integer constants
	public enum AccountType {CHECKING, SAVINGS, MAXI_SAVINGS};

	// Instance variables
	private AccountType accType;
	private List<Transaction> transactions;

	// Constructor
	public Account(AccountType accType) {
		this.accType = accType;
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

	// Instead of duplicating code, 
	// call deposit method with negative value
	public void withdraw(double amount) {
		deposit(-amount);
	}

	public double interestEarned() {
		double amount = sumTransactions();
		switch (accType) {
		case SAVINGS:
			if (amount <= 1000)
				return amount * 0.001;
			else
				return 1 + (amount - 1000) * 0.002;
		
		case MAXI_SAVINGS:
			if (amount <= 1000)
				return amount * 0.02;
			else if (amount <= 2000)
				return 20 + (amount - 1000) * 0.05;
			else return 70 + (amount - 2000) * 0.1;
		
		case CHECKING:
			return amount * 0.001;
		// This should never happen.	
		default:
			return 0;
		}
	}

	// Combined two methods with unused boolean into one 
	public double sumTransactions() {
		double amount = 0.0;
		for (Transaction t : transactions)
			amount += t.amount;
		return amount;
	}

	// Return the account type
	public AccountType getAccountType() {
		return accType;
	}
	
	// Return the account name (new method)
	public String getAccountName() {
		switch (accType) {
		case SAVINGS:
			return "Savings Account\n";
		case MAXI_SAVINGS:
			return "Maxi Savings Account\n";
		case CHECKING:
			return "Checking Account\n";
		default:
			return "Unknown Account\n";
		}
	}
	
	// Return list of transactions in the account(new method)
	public String listTransactions() {
		String s = new String("");
		for (Transaction t : transactions) {
			s+= t.getDetail();
		}
	}
}
