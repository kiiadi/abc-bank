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

	public int getNumberOfAccounts() {
		return accounts.size();
	}

	public double totalInterestEarned() {
		double total = 0;
		for (Account a : accounts)
			total += a.interestEarned();
		return total;
	}

	public String getStatement() {
		StringBuffer statement = new StringBuffer("Statement for " + name + "\n");
		double total = 0.0;
		for (Account a : accounts) {
			statement.append("\n");
			statement.append(statementForAccount(a));
			statement.append("\n");
			total += a.sumTransactions();
		}
		statement.append("\nTotal In All Accounts ");
		statement.append(toDollars(total));
		return statement.toString();
	}

	private String statementForAccount(Account a) {
		// Start with pretty account type
		StringBuffer s = new StringBuffer(a.getAccountType().toString());
		s.append("\n");

		// Now total up all the transactions
		double total = 0.0;
		for (Transaction t : a.transactions) {
			s.append("  ").append(t.getAmount() < 0 ? "withdrawal" : "deposit").append(" ");
			s.append(toDollars(t.getAmount())).append("\n");
			total += t.getAmount();
		}
		s.append("Total ").append(toDollars(total));
		return s.toString();
	}

	private String toDollars(double d) {
		return String.format("$%,.2f", abs(d));
	}
}
