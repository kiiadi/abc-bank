package com.abc;

import static org.junit.Assert.assertEquals;

import java.util.LinkedList;
import java.util.List;
import org.joda.time.DateTime;
import org.junit.Test;

import com.abc.BankConstants.AccountType;
import com.abc.BankConstants.TransactionType;

public class MaxiSavingInterestCalculatorTest {
	private MaxiSavingInterestCalculator interestCalculator  = new MaxiSavingInterestCalculator(); 
	@Test
	public void testAccountLessThanTenDays()
	{
		DateTime now = new DateTime();
		List<Transaction> transactions = new LinkedList<Transaction>();
		transactions.add( new Transaction( TransactionType.DEPOSIT , 10000 ,  now.minusDays(7)) );
		transactions.add( new Transaction( TransactionType.DEPOSIT , 10000 ,  now.minusDays(6)) );
		transactions.add( new Transaction( TransactionType.DEPOSIT , 16500 ,  now.minusDays(5)) );
		Account maxiSavingAccount = new Account(AccountType.MAXI_SAVINGS, transactions, interestCalculator, 36500);
		double interest = interestCalculator.calculate(maxiSavingAccount);
		assertEquals(0.1,interest,BankTestConstants.DOUBLE_DELTA );
	}
	@Test
	public void testAccountWithNoWithdrawal()
	{
		DateTime now = new DateTime();
		List<Transaction> transactions = new LinkedList<Transaction>();
		transactions.add( new Transaction( TransactionType.DEPOSIT , 10000 ,  now.minusDays(17)) );
		transactions.add( new Transaction( TransactionType.DEPOSIT , 10000 ,  now.minusDays(6)) );
		transactions.add( new Transaction( TransactionType.DEPOSIT , 16500 ,  now.minusDays(5)) );
		Account maxiSavingAccount = new Account(AccountType.MAXI_SAVINGS, transactions, interestCalculator, 36500);
		double interest = interestCalculator.calculate(maxiSavingAccount);
		assertEquals(5,interest,BankTestConstants.DOUBLE_DELTA );
	}
	@Test
	public void testAccountMoreThanTenDaysNoWithdrawal()
	{
		DateTime now = new DateTime();
		List<Transaction> transactions = new LinkedList<Transaction>();
		transactions.add( new Transaction( TransactionType.DEPOSIT , 20000 ,  now.minusDays(17)) );
		transactions.add( new Transaction( TransactionType.WITHDRAWAL , 10000 ,  now.minusDays(11)) );
		transactions.add( new Transaction( TransactionType.DEPOSIT , 26500 ,  now.minusDays(5)) );
		Account maxiSavingAccount = new Account(AccountType.MAXI_SAVINGS, transactions, interestCalculator, 36500);
		double interest = interestCalculator.calculate(maxiSavingAccount);
		assertEquals(5,interest,BankTestConstants.DOUBLE_DELTA );
	}
	@Test
	public void testAccountWithWithdralLessThanTenDays()
	{
		DateTime now = new DateTime();
		List<Transaction> transactions = new LinkedList<Transaction>();
		transactions.add( new Transaction( TransactionType.DEPOSIT , 20000 ,  now.minusDays(17)) );
		transactions.add( new Transaction( TransactionType.WITHDRAWAL , 10000 ,  now.minusDays(7)) );
		transactions.add( new Transaction( TransactionType.DEPOSIT , 26500 ,  now.minusDays(5)) );
		Account maxiSavingAccount = new Account(AccountType.MAXI_SAVINGS, transactions, interestCalculator, 36500);
		double interest = interestCalculator.calculate(maxiSavingAccount);
		assertEquals(0.1,interest,BankTestConstants.DOUBLE_DELTA );	
	}
}
