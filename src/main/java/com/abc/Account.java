package com.abc;

import java.util.ArrayList;
import java.util.List;

public abstract class Account implements InterestPayable {
	private final AccountType type ;
	private final AccountActivity acctActivity ;
	
	public Account(AccountType type, double startingBalance) {
		super();
		acctActivity = new AccountActivity(0.0) ;
		this.type = type ;
	}

	public void deposit(final double amount) throws InvalidAccountTransaction {
	    this.processTransaction(TransactionType.Deposit, amount) ;
	}

	public void processTransaction(TransactionType type, final double amount) throws InvalidAccountTransaction {
		if ( amount < 0 ) {
			throw new InvalidAccountTransaction("Transaction amount is less than zero");
		}
		acctActivity.processTransaction(type, amount);
	}

	public void withdraw(final double amount) throws InvalidAccountTransaction {
	        this.processTransaction(TransactionType.Withdrawal, amount);
	}
	
	/* (non-Javadoc)
	 * @see com.abc.InterestPayable#interestEarned()
	 */
	public double interestEarned()  {
		return ( acctActivity.getCurrBalance() * .001 ) ;
	}

	public double getCurrBalance() {
	   return this.acctActivity.getCurrBalance();
	}

	public AccountType getType() {
		return type;
	}
	
	public List<Transaction> transactions() {
		/* return new list so that current activity does not escape current thread */
		return acctActivity.transactions() ;
	}

}