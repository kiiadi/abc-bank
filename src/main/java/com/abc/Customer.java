package com.abc;

import java.util.ArrayList;
import java.util.List;

import com.abc.account.Account;
import com.abc.util.BankUtils;

public class Customer {
	private String name;
	private Long id;
	private List<Account> accounts;

	public Customer(Long id, String name) {
		this.id = id;
		this.name = name;
		this.accounts = new ArrayList<Account>();
	}

	public Long getId() {
		return id;
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
		StringBuilder statement = new StringBuilder();
		statement.append("Statement for ").append(name).append("\n");
		double total = 0.0;
		for (Account a : accounts) {
			statement.append("\n").append(a.statementForAccount()).append("\n");
			total += a.sumTransactions();
		}
		statement.append("\nTotal In All Accounts ").append(
				BankUtils.toDollars(total));
		return statement.toString();
	}
}
