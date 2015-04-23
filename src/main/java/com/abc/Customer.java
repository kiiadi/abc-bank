package com.abc;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.abs;

public class Customer {
	private String name;
	private List<Account> accounts;

	public Customer(String name) {
		this.name = name;
		this.accounts = new ArrayList<Account>();
	}

	public String getName() {
		return name;
	}

	public Customer openAccount(Account account) {
		accounts.add(account);
		return this;
	}
	
	public void accountDeposit(Account account, double amount){
		account.deposit(amount);	
	}
	
	public void accountwithdraw(Account account, double amount) {
		account.withdraw(amount);
	}
	
	public boolean transferMoney(Account fromAccount, Account toAccount, double amount) {
		if (fromAccount.withdraw(amount) == amount) {
			toAccount.deposit(amount);
			return true;
		}
		return false;
	}

	public int getNumberOfAccounts() {
		return accounts.size();
	}

	public double totalInterestEarned() {
		double total = 0;
		for (Account a : accounts)
			total += a.getInterestEarned();
		return total;
	}

	public String getStatement() {
		String statement = null;
		statement = "Statement for " + name + "\n";
		double total = 0.0;
		for (Account a : accounts) {
			statement += "\n" + a.getStatement() + "\n";
			total += a.getAccountBalance();
		}
		statement += "Total In All Accounts " + toDollars(total);
		return statement;
	}

	private String toDollars(double d) {
		return String.format("$%,.2f", abs(d));
	}
}
