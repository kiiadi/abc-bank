package com.abc;

import static java.lang.Math.abs;

import java.util.ArrayList;
import java.util.List;

import com.abc.account.Account;

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

	public void transfer(Account fromAccount, Account toAccount, double amount)
			throws Exception {

		if (fromAccount == null) {
			throw new IllegalArgumentException("fromAccount must be specified");
		}

		if (toAccount == null) {
			throw new IllegalArgumentException("toAccount must be specified");
		}

		if (amount <= 0) {
			throw new IllegalArgumentException(
					"amount must be greater than zero");
		}

		if (getNumberOfAccounts() <= 1) {
			throw new Exception(
					"customer must have more than 1 accounts for transfer");
		}

		if (amount > fromAccount.balance()) {
			throw new Exception("fromAccount has insufficient balance");
		}

		synchronized (this) {
			fromAccount.withdraw(amount);
			toAccount.deposit(amount);
		}

	}

	public int getNumberOfAccounts() {
		return accounts.size();
	}

	public double totalInterestEarned() {
		double total = 0;
		for (Account a : accounts) {
			total += a.interestEarned();
		}
		return total;
	}

	public String getStatement() {

		StringBuilder sb = new StringBuilder();
		sb.append("Statement for ");
		sb.append(name);
		sb.append("\n");

		double total = 0.0;
		for (Account a : accounts) {

			sb.append("\n");
			sb.append(statementForAccount(a));
			sb.append("\n");

			total += a.sumTransactions();
		}
		sb.append("\nTotal In All Accounts ");
		sb.append(toDollars(total));

		return sb.toString();
	}

	// TODO StringBuilder
	private String statementForAccount(Account a) {
		String s = "";

		s += a.getLabel();

		// Now total up all the transactions
		double total = 0.0;
		for (Transaction t : a.getTransactions()) {
			s += "  " + (t.getAmount() < 0 ? "withdrawal" : "deposit") + " "
					+ toDollars(t.getAmount()) + "\n";
			total += t.getAmount();
		}
		s += "Total " + toDollars(total);
		return s;
	}

	private String toDollars(double d) {
		return String.format("$%,.2f", abs(d));
	}
}
