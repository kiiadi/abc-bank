package com.abc;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;


/*
 * 
 * Alex Lerner updates ( AlecLerner@gmail.com
 * 
 */


public class Customer {
	private static final String EOL = "\n";
	private static final String DEPOSIT = "deposit";
	private static final String WITHDRAWAL = "withdrawal";
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

	public BigDecimal totalInterestEarned() {
		BigDecimal total = new BigDecimal(0);
		for (Account a : accounts)
			total = total.add( a.interestEarned() );
		return total;
	}

	public String getStatement() {
		StringBuilder statement = new StringBuilder();
		statement.append("Statement for ").append(name).append(EOL);
				BigDecimal total = new BigDecimal(0);
				for (Account a : accounts) {
					statement.append(EOL).append(statementForAccount(a)).append(EOL);
					total = total.add( a.sumTransactions() );
				}
				statement.append("\nTotal In All Accounts ").append(toDollars(total));
				return statement.toString();
	}

	private static String statementForAccount(Account a) {
		StringBuilder s = new StringBuilder();

		s.append(a.getAccountDescription()).append(EOL);

		//Now total up all the transactions

		BigDecimal total = new BigDecimal(0);
		for (Transaction t : a.getTransactions()) {
			s.append("  ").append((t.getAmount().intValue() < 0 ? WITHDRAWAL : DEPOSIT)).append(" ").append(toDollars(t.getAmount())).append(EOL);
			total = total.add(t.getAmount());
		}
		s.append("Total ").append(toDollars(total));
		return s.toString();
	}

	private static String toDollars(BigDecimal d){
		return String.format("$%,.2f", d.abs());
	}

	public static void tansfer(Account fromAccount, Account toAccount, double amount) throws Exception {
		if ( (fromAccount == null) || ( toAccount == null) || fromAccount == toAccount ) {
			throw new IllegalArgumentException("account can't be null or equal");
		}

		if ( fromAccount.sumTransactions().compareTo(new BigDecimal( amount)) == -1 ) {
			throw new Exception( amount + " exceeds amount of " + fromAccount.getAccountDescription());
		}

		fromAccount.withdraw(amount);
		toAccount.deposit(amount);
	}

}
