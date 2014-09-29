package com.abc.account;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.abc.account.interest.calculator.IInterestCalculator;
import com.abc.exception.TransferFailedException;
import com.abc.exception.ValidationException;
import com.abc.transaction.Deposit;
import com.abc.transaction.ITransaction;
import com.abc.transaction.Withdraw;
import com.abc.transaction.validator.ITransactionValidator;
import com.abc.transaction.validator.WithdrawValidator;

/**
 * 
 * @author Sanju Thomas
 *
 */
public abstract class Account implements IAccount{
	
	protected List<ITransactionValidator> transactionValidators;
	
	protected List<ITransaction> transactions;
	
	protected IInterestCalculator interestCalculator;
	
	private double balance;
	
	protected Date openingDate;
	
	private String number;
	
	protected Account(final String number){
		this.number = number;
	}

	public synchronized void process(final Deposit deposit) {
		this.balance += deposit.getAmount();
		transactions.add(deposit);
	}

	public synchronized void process(final Withdraw withdraw) throws ValidationException{
		for(final ITransactionValidator transactionValidator : transactionValidators){
			transactionValidator.validate(this, withdraw);
		}
		this.balance -= withdraw.getAmount();
		transactions.add(withdraw);
	}

	public List<ITransaction> getTransactions() {
		return new ArrayList<ITransaction>(transactions);
	}
	
	public double getBalance(){
		return balance;
	}
	
	public Date getOpeningDate(){
		return this.openingDate;
	}
	
	public String getNumber(){
		return this.number;
	}
	
	/**
	 *  Transfer money from this account to given target account.
	 *  since withdraw and deposit methods are thread safe this one not necessary be synchronized
	 */
	public void transfer(final IAccount targetAccount, final double amount) throws ValidationException{
		final Withdraw withdraw = new Withdraw(amount);
		this.process(withdraw);
		//send money to the target account
		final Deposit deposit = new Deposit(amount);
		try {
			targetAccount.process(deposit);
		} catch (final Exception e) {
			this.process(deposit);
			throw new TransferFailedException("Transfer failed due to unexpected reasons, please contact the customer support.");
		}
	}
	
	/**
	 * Calculate the interest earned so far.
	 * 
	 */
	public double getInterestEarned(){
		double interest = 0;
		if(null != interestCalculator){
			interest = interestCalculator.calculate(this);
		}
		return interest;
	}

	/**
	 * @Todo
	 *  
	 * wire these validators using an IOC container in real implementation 
	 * and make the visibility of transactionValidators private
	 * 
	 * @return
	 */
	protected List<ITransactionValidator> getTransactionValidators(){
		final List<ITransactionValidator> transactinValidators = new ArrayList<ITransactionValidator>();
		transactinValidators.add(new WithdrawValidator());
		return transactinValidators;
	}
}
