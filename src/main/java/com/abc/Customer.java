package com.abc;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.abs;

/**
 * Customer represents our Bank's customer. 
 * @author Prachi
 */
public class Customer {
	/**
	 * customer name
	 */
	private String name;

	/**
	 * list of accounts for customer
	 */
	private List<Account> accounts;

	/**
	 * Initialize the customer with name and list of accounts
	 * @param name customer name
	 */
	public Customer(String name) {
		this.name = name;
		this.accounts = new ArrayList<Account>();
	}

	/**
	 * Getter for customer name
	 * @return customer name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Opens account for the customer
	 * @param Account having it's type information to be opened
	 * @return customer object enriched with a new account
	 */
	public Customer openAccount(Account account) {
		accounts.add(account);
		return this;
	}

	/**
	 * Gets the number of account for the customer
	 * @return number of accounts
	 */
	public int getNumberOfAccounts() {
		return accounts.size();
	}

	/**
	 * Calculates total interest of all the accounts for the customer
	 * @return total interest earned
	 */
	public double totalInterestEarned() {
		double total = 0;
		for (Account a : accounts)
			total += a.interestEarned();
		return total;
	}

	/**
	 * Gives statement for the customer
	 * @return statement for all the accounts
	 */
	public String getStatement() {
		String statement = null;
		statement = "Statement for " + name + "\n";
		double total = 0.0;
		for (Account a : accounts) {
			statement += "\n" + statementForAccount(a) + "\n";
			total += a.getTotalAccountBalance();
		}
		statement += "\nTotal In All Accounts " + toDollars(total);
		return statement;
	}

	/**
	 * Gives statement for the customer for particular account
	 * @param account 
	 * @return statement for particular account
	 */	
	private String statementForAccount(Account a) {
		double total = 0.0;
		
		// modified to use StringBuilder instead of String to prevent unnecessary object creation
		StringBuilder s = new StringBuilder();
		s.append(a.getAccountType()+" Account \n");
		for (Transaction t : a.getTransactions()) {
			s.append("  " + (t.getAmount() < 0 ? "withdrawal" : "deposit") + " " + toDollars(t.getAmount()) + "\n");
			total += t.getAmount();
		}
		s.append("Total " + toDollars(total));
		return s.toString();
	}
	
	/**
	 * Converts the amount in dollar format
	 * @param amount
	 * @return amount in dollar format
	 */
	private String toDollars(double d){
		return String.format("$%,.2f", abs(d));
	}

	/**
	 * Transfers amount between two accounts of the customer.
	 * @param creditAccount
	 * @param debitAccount
	 * @param amount
	 * @return true if the transfer takes places successfully or false. 
	 */
	public boolean transferAmount(Account creditAccount, Account debitAccount, double amount ) {
		boolean isTransferred=false;
		if(creditAccount == null || debitAccount == null){
			throw new RuntimeException("Invalid Credit or Debit account input"); 
		}		
		//if deposit/withdraw doesn't happen successfully then the transaction is rolled back.
		debitAccount.withdraw(amount);
		try{
			creditAccount.deposit(amount);
			isTransferred = true;
		}
		catch(Exception e){
			debitAccount.deposit(amount);
		}
		return isTransferred;
	}
}