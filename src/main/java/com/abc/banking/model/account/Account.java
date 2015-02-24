package com.abc.banking.model.account;

import java.util.List;

import com.abc.banking.exception.BankingCriticalException;
import com.abc.banking.transaction.Transaction;

/**
 * Interface to handle account related operations
 */
public interface Account {		

	/**
	 * Deposit funds to a account
	 * @param amount
	 * @throws BankingCriticalException
	 */
	public void deposit(double amount) throws BankingCriticalException;
	
	/**
	 * withdraw funds from a account
	 * @param amount
	 * @throws BankingCriticalException
	 */
	public void withdraw(double amount) throws BankingCriticalException;
	
	/**
	 * calculate the the interest on account
	 * @return
	 * @throws BankingCriticalException
	 */
	public double calculateInterest() throws BankingCriticalException;
	
	/**
	 * get the account balance
	 * @return
	 */
	public double getAccountBalance();
	
	/**
	 * get all transactions done on this account
	 * @return
	 */
	public List<Transaction> getTransactions ();
	
	
	

}
