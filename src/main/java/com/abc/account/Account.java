package com.abc.account;

public interface Account {

	public void deposit(double amount);

	public void withdraw(double amount);

	public double interestEarned();

	public double sumTransactions();

	public AccountType getAccountType();

	public String statementForAccount();

}