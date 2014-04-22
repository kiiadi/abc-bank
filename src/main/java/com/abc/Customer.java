package com.abc;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.abc.account.Account;
import com.abc.account.Transaction;

public class Customer {
	private String name;
	private List<Account> accounts;

	private static final String DOUBLE_SPACE = "  ";
	private static final String SPACE = " ";
	private static final String NEWLINE = "\n";

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

	public BigDecimal totalInterestEarned() {
		BigDecimal total = BigDecimal.ZERO;
		for (Account a : accounts)
			total = total.add(a.interestEarned());
		return total;
	}
	
	/**
	 * TODO: Statement should be encapsulated in its own class and use the
	 * Statement instance for assertions. Asserting on formatted String is tedious and error prone.
	 * Also, printing/formatting should be decoupled from the entity representation. Statement is more
	 * of a domain model rather than a print feature.
	 */
	public String getStatement() {
		StringBuilder statement = new StringBuilder();
		statement.append("Statement for ").append(name).append(NEWLINE);
		BigDecimal total = BigDecimal.ZERO;
		for (Account a : accounts) {
			statement.append(NEWLINE).append(statementForAccount(a))
					.append(NEWLINE);
			total = total.add(a.sumTransactions());
		}
		statement.append(NEWLINE).append("Total In All Accounts ")
				.append(toDollars(total));
		return statement.toString();
	}

	private String statementForAccount(Account a) {

		StringBuilder sb = new StringBuilder();
		sb.append(a.getType().getDescription()).append(NEWLINE);
		BigDecimal total = a.sumTransactions();
		for (Transaction t : a.getTransactions()) {
			sb.append(DOUBLE_SPACE).append(t.getType().name()).append(SPACE)
					.append(toDollars(t.getAmount())).append(NEWLINE);
		}
		sb.append("Total ").append(toDollars(total));
		return sb.toString();
	}

	private String toDollars(BigDecimal d) {
		return String.format("$%,.2f", d.doubleValue());
	}
}
