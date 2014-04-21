/**
 * 
 */
package com.abc.accounts;

import java.util.ArrayList;
import java.util.List;

import com.abc.exceptions.InsufficientFundsException;
import com.abc.exceptions.InvalidTransactionAmountException;

/**
 * Contains common attributes and behaviors of a bank account.
 * 
 */
public abstract class Account {
	/**
	 * Add unique account id field to support multiple accounts with the same accountType
	 * for a given customer
	 */
	private int accountId;
	
	protected AccountType accountType;
	private List<Transaction> transactions;
	
	public Account(AccountType accountType) {
		this.accountId = getNextId();  // generate universally unique id for the account
        this.accountType = accountType;
        this.transactions = new ArrayList<Transaction>();
    }
	
	/***
	 * Can be a UUID in java or a sequence db object in the real-world
	 */
	private static int idCounter = 0;
	private static int getNextId() {
		return ++idCounter;
	}

	public int getAccountId() {
		return accountId;
	}
	
	public AccountType getAccountType() {
		return accountType;
	}

	public List<Transaction> getTransactions() {
		return transactions;
	}

	/**
	 * @param amount - should be greater than zero
	 * @throws InvalidTransactionAmountException 
	 */
	public void deposit(double amount) throws InvalidTransactionAmountException {
        if (amount <= 0) {
            throw new InvalidTransactionAmountException("Deposit amount must be greater than zero");
        }
        
        transactions.add(new Transaction(amount));
    }
	
	/**
	 * @param amount - should be greater than zero
	 * @throws InvalidTransactionAmountException, InsufficientFundsException
	 */
	public void withdraw(double amount)
			throws InvalidTransactionAmountException, InsufficientFundsException {
	    if (amount <= 0) {
	        throw new InvalidTransactionAmountException("Withdrawal amount must be greater than zero");
	    }
	    
	    // TODO: can create a new exception
	    if(!hasSufficientFunds(amount)) {
	    	throw new InsufficientFundsException("Account has insufficient funds - " + getAccountId());
	    }
	    
	    transactions.add(new Transaction(-amount));
	}
	
	private boolean hasSufficientFunds(double amount) {
		return (getBalance() >= amount);
	}
	
	@Override
	public String toString() {
		return "Abstract base account class";
	}
	
	public abstract double interestEarned();
	
	public double getBalance() {
		double amount = 0.0;
		
        for (Transaction transaction: transactions) {
            amount += transaction.getAmount();
        }
        
        return amount;
	}
}
