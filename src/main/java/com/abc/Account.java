package com.abc;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Account {
	private int accountNumber;
	private AccountType accountType;
	public List<Transaction> transactions;
	// Object aLock = new Object();
	private final Lock accountLock = new ReentrantLock();

	public Account(AccountType accountType) {
		this.accountType = accountType;
		this.transactions = new ArrayList<Transaction>();
	}

	public void deposit(double amount) {

		if (amount <= 0) {
			throw new IllegalArgumentException(
					"amount must be greater than zero");
		} else {
			// synchronized (aLock) {
			accountLock.lock();
			try {
				transactions.add(new Transaction(amount));

			} finally {
				accountLock.unlock();
			}
		}

	}

	public void withdraw(double amount) {
		//double sumTransactions = sumTransactions();
		if (amount <= 0 ) {
			throw new IllegalArgumentException(
					"Withdrawal amount must be greater than zero");
		} else {
			// synchronized (aLock) {
			accountLock.lock();
			try {
				transactions.add(new Transaction(-amount));

			} finally {
				accountLock.unlock();
			}
		}
	}

	public double interestEarned() {
		double amount = sumTransactions();
		IInterestStrategy str = new InterestStrategyImpl(accountType);
		return str.calculateInterest(amount);
	}

	public double sumTransactions() {
		return checkIfTransactionsExist(true);
	}

	private double checkIfTransactionsExist(boolean checkAll) {
		double amount = 0.0;
		// synchronized (aLock) {
		accountLock.lock();
		try {
			for (Transaction t : transactions)
				amount += t.amount;
		} finally {
			accountLock.unlock();
		}
		return amount;
	}

	public AccountType getAccountType() {
		return accountType;
	}

	public int getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(int accountNumber) {
		this.accountNumber = accountNumber;
	}

}
