package com.abc.account;

import java.util.Date;
import java.util.List;

import com.abc.exception.ValidationException;
import com.abc.transaction.Deposit;
import com.abc.transaction.ITransaction;
import com.abc.transaction.Withdraw;

/**
 * 
 * @author Sanju Thomas
 *
 */
public interface IAccount {

	public void process(final Deposit deposit);

	public void process(final Withdraw withdraw) throws ValidationException;

	public List<ITransaction> getTransactions();
	
	public double getBalance();
	
	public void transfer(final IAccount targetAccount, final double amount) throws ValidationException;
	
	public Date getOpeningDate();
	
	public double getInterestEarned();
	
}
