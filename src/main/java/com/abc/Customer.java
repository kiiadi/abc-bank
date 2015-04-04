package com.abc;

import java.util.LinkedList;
import java.util.List;
import static java.lang.Math.abs;

public class Customer {
	private String name;
	private List<Account> accounts;

	public Customer(String name) {
		this.name = name;
		this.accounts = new LinkedList<Account>();
	}

	public String getName() {
		return name;
	}


	public Customer openAccount(Account account) {
		accounts.add(account);
		account.setCustomerName(name);
		return this;
	}

	public int getNumberOfAccounts() {
		return accounts.size();
	}

	public double totalInterestEarned() {
		double total = 0;
		for (Account a : accounts)
			total += a.interestEarned();
		return total;
	}

	public void transferAmount(Account from, Account to, double amount) {
		if( from.getCustomerName() == null )
			throw new IllegalArgumentException(
			"Invalid account, not linked with any customer");
		if(! from.getCustomerName().equals(to.getCustomerName()))
			throw new IllegalArgumentException(
			"Accounts belongs to different customers");
		from.withdraw(amount);
		to.deposit(amount);
	}
	public String getStatement() {
		StringBuilder statement = new StringBuilder("Statement for ").append(name).append("\n");
		double total = 0.0;
		for (Account a : accounts) {
			statement.append("\n").append(statementForAccount(a)).append("\n");
			total += a.getAmount();
		}
		statement.append("\nTotal In All Accounts ").append(toDollars(total));
		return statement.toString();
	}
	
	private String statementForAccount(Account a) {
		StringBuilder sb =  new StringBuilder( a.getAccountType().getValue()).append(" Account\n");
		for (Transaction t : a.transactions) 
			sb.append("  ").append(t.getTransactionType().getValue()).append(" ").append(toDollars(t.getAmount())).append
			("\n");
		sb.append("Total ").append(toDollars(a.getAmount()));				
		return sb.toString();
	}

	private String toDollars(double d) {
		return String.format("$%,.2f", abs(d));
	}
}
