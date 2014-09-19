package com.abc;

import java.util.ArrayList;
import java.util.List;

public class Account {

	private AccountType accountType;
	private double balance;
	private List<Transaction> transactions;

	public Account(AccountType accountType) {
		this.accountType = accountType;
		transactions = new ArrayList<Transaction>();
	}

	public AccountType getAccountType() {
		return accountType;
	}

	public double getBalance() {
		return balance;
	}

	public void deposit(double amount) {
		if (amount <= BankConstants.ZERO_AMOUNT)
			throw new IllegalArgumentException(BankConstants.INVALID_AMOUNT);
		balance = balance + amount;
		transactions.add(new Transaction(amount));
	}

	public void withdraw(double amount) {
		if (amount <= balance) {
			balance = balance - amount;
			transactions.add(new Transaction(BankConstants.NEGATIVE_MULTIPLIER * amount));
		} else {
			throw new IllegalArgumentException(BankConstants.INSUFFICIENT_FUNDS);
		}
	}

	public void transfer(double amount, Account otherAccount) {
		this.withdraw(amount);
		otherAccount.deposit(amount);
	}

	public double interestEarned() {
		return new InterestRateFactory().rateCalculator(accountType)
				.calculateEarned(balance);
	}

	public List<Transaction> getAllTransactions() {
		return transactions;
	}

	
	
	
	
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((accountType == null) ? 0 : accountType.hashCode());
		long temp;
		temp = Double.doubleToLongBits(balance);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result
				+ ((transactions == null) ? 0 : transactions.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Account other = (Account) obj;
		if (accountType != other.accountType)
			return false;
		if (Double.doubleToLongBits(balance) != Double
				.doubleToLongBits(other.balance))
			return false;
		if (transactions == null) {
			if (other.transactions != null)
				return false;
		} else if (!transactions.equals(other.transactions))
			return false;
		return true;
	}
	
	
	

}
