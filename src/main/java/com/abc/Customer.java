package com.abc;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Currency;
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

	public void transfer(Account fromAccount, Account toAccount,
			BigDecimal amount) throws Exception {

		if (fromAccount == null) {
			throw new IllegalArgumentException("fromAccount must be specified");
		}

		if (toAccount == null) {
			throw new IllegalArgumentException("toAccount must be specified");
		}

		if (amount.compareTo(new BigDecimal("0")) <= 0) {
			throw new IllegalArgumentException(
					"amount must be greater than zero");
		}

		if (getNumberOfAccounts() <= 1) {
			throw new Exception(
					"customer must have more than 1 accounts for transfer");
		}
		if (amount.compareTo(fromAccount.sumTransactions()) > 0) {
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

	public BigDecimal totalInterestEarned() {
		BigDecimal total = new BigDecimal("0");
		for (Account a : accounts) {
			total = total.add(a.interestEarned());
		}
		return total;
	}

	public String getStatement() {

		StringBuilder sb = new StringBuilder();
		sb.append("Statement for ");
		sb.append(name);
		sb.append("\n");

		BigDecimal total = new BigDecimal("0");
		for (Account a : accounts) {

			sb.append("\n");

			sb.append(statementForAccount(a));
			sb.append("\n");

			total = total.add(a.sumTransactions());
		}
		sb.append("\nTotal In All Accounts ");

		sb.append(toDollars(total));

		return sb.toString();
	}

	private String statementForAccount(Account account) {

		StringBuilder sb = new StringBuilder();

		sb.append(account.getLabel());

		// Now total up all the transactions
		BigDecimal total = new BigDecimal("0.00");

		for (Transaction transaction : account.getTransactions()) {
			sb.append("  ");
			sb.append(((transaction.getAmount().compareTo(
					new BigDecimal("0.00")) < 0) ? "withdrawal" : "deposit"));
			sb.append(" ");
			sb.append(toDollars(transaction.getAmount()));
			sb.append("\n");

			total = total.add(transaction.getAmount());
		}
		sb.append("Total ");
		sb.append(toDollars(total));

		return sb.toString();
	}

	private String toDollars(BigDecimal amount) {

		NumberFormat nf = NumberFormat.getCurrencyInstance();
		nf.setCurrency(Currency.getInstance("USD"));

		return nf.format(amount.abs());

	}
}
