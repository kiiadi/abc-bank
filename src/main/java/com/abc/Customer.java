package com.abc;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.abs;

/**
 * Class representing Bank customer
 * @author Rakesh
 *
 */
public class Customer {
	/**
	 * Customer name
	 */
	private String name;
	
	/**
	 * List of accounts for a customer
	 */
	private List<Account> accounts;

	/**
	 * Constructor
	 * @param name
	 */
	public Customer(String name) {
		this.name = name;
		this.accounts = new ArrayList<Account>();
	}

	/**
	 * Getter method for customer name
	 * @return Customer name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Open account for a customer
	 * @param account
	 * @return
	 */
	public Customer openAccount(Account account) {
		accounts.add(account);
		return this;
	}

	/**
	 * Number of account for a customer
	 * @return number of accounts
	 */
	public int getNumberOfAccounts() {
		return accounts.size();
	}

	/**
	 * Calculate total interest across all accounts
	 * @return total interest
	 */
	public double totalInterestEarned() {
		double total = 0;
		for (Account a : accounts)
			total += a.interestEarned();
		return total;
	}

	/**
	 * Generate statement covering all accounts
	 * @return Statement details
	 */
	public String getStatement() {
		String statement = null;
		statement = "Statement for " + name + "\n";
		double total = 0.0;
		for (Account a : accounts) {
			statement += "\n" + statementForAccount(a) + "\n";
			total += a.sumTransactions();
		}
		statement += "\nTotal In All Accounts " + toDollars(total);
		return statement;
	}

	/**
	 * Generate statement for a single account
	 * @param a
	 * @return single account statement
	 */
	private String statementForAccount(Account a) {
		String accountDescription = a.getAccountType().getAccountDescription().trim();

		// Now total up all the transactions
		double total = 0.0;
		for (Transaction t : a.getTransactions()) {
			accountDescription += "\n"
					+ (t.getAmount() < 0 ? "  withdrawal" : "  deposit") + " "
					+ toDollars(t.getAmount()) + "";
			total += t.getAmount();
		}
		accountDescription += "\n" + "Total " + toDollars(total);
		return accountDescription;
	}

	private String toDollars(double d) {
		return String.format("$%,.2f", abs(d));
	}

	/**
	 * Transfer fund between accounts for a given customer
	 * Basic check is done that same account is not used
	 * @param fromAccount
	 * @param toAccount
	 * @param amount
	 */
	public void transferFunds(Account fromAccount, Account toAccount,
			double amount) {
		if (fromAccount.getAccountType() == toAccount.getAccountType()) {
			throw new IllegalArgumentException(
					"fund transfer must be between different accounts");
		}
		fromAccount.withdraw(amount);
		toAccount.deposit(amount);

	}
}
