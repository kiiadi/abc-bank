package com.abc;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class Customer {
	private static NumberFormat usFormat = NumberFormat.getCurrencyInstance(Locale.US);
	private String name;
	private List<Account> accounts;

	public Customer(String name) {
		this.name = name;
		this.accounts = new ArrayList<>();
	}

	public String getName() {
		return name;
	}

	public Customer openAccount(Account account) {
		accounts.add(account);
		return this;
	}

	/**
	 * Transfer money between accounts
	 *
	 * @param amount money to be transferred
	 * @param fromAccount account to be debited
	 * @param toAccount account to be credited
	 */
	public void performTransfer(double amount, Account fromAccount, Account toAccount) {
		// todo: insure that the following two operations are done atomically
		try {
			fromAccount.withdraw(amount);
			toAccount.deposit(amount);
		} catch (AccountModificationException amf) {
			System.out.println("***** Error performing account transfer: " + amf.getMessage());
		}
	}

	public int getNumberOfAccounts() {
		return accounts.size();
	}

	public double totalInterestEarned() {
		double total = 0;
		for (Account a : accounts) {
			total += a.calculateBalanceAndInterest(new Date());
		}

		return total;
	}

	public String generateStatement() {
		StringBuffer statement = new StringBuffer("Statement for ");
		statement.append(name).append("\n");
		double total = 0.0;

		for (Account a : accounts) {
			statement.append("\n").append(a.statement()).append("\n");
			total += a.getBalance();
		}

		statement.append("\nTotal In All Accounts ");
		statement.append(usFormat.format(total));
		return statement.toString();
	}

}
