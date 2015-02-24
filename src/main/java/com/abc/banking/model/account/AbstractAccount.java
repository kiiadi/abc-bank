package com.abc.banking.model.account;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.abc.banking.exception.BankingCriticalException;
import com.abc.banking.transaction.Transaction;
import com.abc.banking.transaction.TransactionType;

/**
 * Implements the common features for any account type
 *
 */
public abstract class AbstractAccount implements Account{	

	/**
	 *  account balance
	 */
	protected double accountBalance = 0.0;
	/**
	 *  Transaction associated with account
	 */
	protected List<Transaction> transactions = new ArrayList<Transaction>();
	

	/* (non-Javadoc)
	 * @see com.abc.banking.model.account.Account#deposit(double)
	 */
	@Override
	public synchronized void deposit(double amount) throws BankingCriticalException{

		if (amount > 0) {			 
			accountBalance = accountBalance + amount ;
			transactions.add(new Transaction(amount, "MONEY DEPOSITED", TransactionType.CREDIT)) ;

		} else  {
			throw new BankingCriticalException("Deposited amount must be greater than 0");
		}
	}
	
	
	/* (non-Javadoc)
	 * @see com.abc.banking.model.account.Account#withdraw(double)
	 */
	@Override
	public synchronized void withdraw(double amount) throws BankingCriticalException {

		if (amount > 0) {
			if (amount > accountBalance){
				throw new BankingCriticalException("Insufficient balance in account") ;
			} else {
				accountBalance = accountBalance - amount;
				transactions.add(new Transaction(amount, "MONEY WITHDRAWN", TransactionType.DEBIT)) ;				 
			}
		} else {
			throw new BankingCriticalException("Withdrawn amount must be greater than 0");
		}

	}
	
	/**
	 * This method returns the number of days since a transaction type (credit/debit)
	 * was done on this account
	 * @param transactionType
	 * @return number of days
	 */
	public int getLastTransactionAge(TransactionType transactionType){	
		
		Date currentDate = Calendar.getInstance().getTime();
		
		for (int index = transactions.size()-1 ; index >= 0 ; index --){
			Transaction lastTransaction =  transactions.get(index) ;
			if (lastTransaction.getTransactionType().equals(transactionType)) {					
				Date transactionDate = lastTransaction.getTransactionDate();
				return (int)Math.round(( (currentDate.getTime() - transactionDate.getTime()) / (1000 * 60 * 60 * 24) ));				
			} 
		 
		}
		return -1  ;
	}
	
	/**
	 * transfer funds from this account to another
	 * @param toAccount
	 * @param amount
	 * @throws BankingCriticalException
	 */
	public synchronized void transferFund(Account toAccount , double amount) throws BankingCriticalException{
		
		this.withdraw(amount);
		toAccount.deposit(amount);
		
	}


	/* (non-Javadoc)
	 * @see com.abc.banking.model.account.Account#getAccountBalance()
	 */
	public double getAccountBalance() {
		return accountBalance;
	}

	/* (non-Javadoc)
	 * @see com.abc.banking.model.account.Account#getTransactions()
	 */
	public List<Transaction> getTransactions() {
		return transactions;
	}

}
