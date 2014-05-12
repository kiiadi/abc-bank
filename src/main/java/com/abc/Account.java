package com.abc;

import java.util.ArrayList;
import java.util.List;

/**
 * a class representing a customer bank account.
 * 
 * @author Jeff
 * 
 */
public class Account
    {

	// possible bank account types
	public static final int CHECKING = 0;
	public static final int SAVINGS = 1;
	public static final int MAXI_SAVINGS = 2;

	private final int accountType;
	public List<Transaction> transactions;

	/**
	 * Create an account object
	 * 
	 * @param accountType
	 */
	public Account(int accountType)
	    {
		this.accountType = accountType;
		this.transactions = new ArrayList<Transaction>();
	    }

	/**
	 * Deposit funds into an existing account
	 * 
	 * @param amount
	 *         - the amount to deposit into the account 
	 */
	public void deposit(double amount)
	    {
		// if the amount is LE zero, then this should throw
		// an exception
		if (amount <= 0)
		    {
			throw new IllegalArgumentException(
				"amount must be greater than zero");
		    }
		// otherwise
		else
		    {

			transactions.add(new Transaction(amount));
		    }
	    }

	/**
	 * Withdraw funds from an existing account
	 * @param amount - the amount to withdraw from the account
	 */
	public void withdraw(double amount)
	    {
		// if the amount of the withdrawal is LE zero, throw an exception.
		if (amount <= 0)
		    {
			throw new IllegalArgumentException(
				"amount must be greater than zero");
		    }
		else // create a new transaction to record the amount of the withdrawal
		    {
			transactions.add(new Transaction(-amount));
		    }
	    }

	/**
	 * calculate interest on the existing balance in the account
	 * @return the amount of interest earned
	 */
	public double interestEarned()
	    {
		double amount = sumTransactions();
		switch (accountType)
		    {
		    case SAVINGS:
			if (amount <= 1000)
			    return amount * 0.001;
			else
			    return 1 + (amount - 1000) * 0.002;
			// case SUPER_SAVINGS:
			// if (amount <= 4000)
			// return 20;
		    case MAXI_SAVINGS:
			if (amount <= 1000)
			    return amount * 0.02;
			if (amount <= 2000)
			    return 20 + (amount - 1000) * 0.05;
			return 70 + (amount - 2000) * 0.1;
		    default:
			return amount * 0.001;
		    }
	    }

	/**
	 * sum the transactions for this account
	 * @return the sum of all transactions
	 */
	public double sumTransactions()
	    {
		return checkIfTransactionsExist(true);
	    }

	/**
	 * Check to see if transactions exist 
	 * 
	 * @param checkAll - check all transactions ** NOT USED **
	 * @return
	 */
	private double checkIfTransactionsExist(boolean checkAll)
	    {
		double amount = 0.0;
		for (Transaction t : transactions)
		    amount += t.amount;
		return amount;
	    }

	/**
	 * Return the account type of this account
	 * @return
	 */
	public int getAccountType()
	    {
		return accountType;
	    }

    }
