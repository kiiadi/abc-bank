package com.abc;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Customer {

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

	public List<Account> getAccounts() {
		return accounts;
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

}
