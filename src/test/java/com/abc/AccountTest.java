package com.abc;

import static org.junit.Assert.assertEquals;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;


import org.junit.Test;

import com.abc.Account;
import com.abc.AccountType;

/**
 * @author Prachi
 */
public class AccountTest {
	
	private static final double DOUBLE_DELTA = 1e-15;
	Calendar cal = GregorianCalendar.getInstance();
	private final Date depositDate = getDate(2014, 1, 28);
	private final Date withdrawBefore10Days = getDate(2014, 1, 02);
	private final Date withdrawWithin10Days =  getDate(2014, 11, 02);

	/**
	 * Validate the interest for maxi saving account 
	 * for withdrawal transaction before 10 days
	 */
	@Test
	public void testMaxiSavingsBeforeCutoff() {
		Account acc = new Account(AccountType.MAXI_SAVINGS);
		acc.deposit(100, depositDate);
		acc.withdraw(50, withdrawBefore10Days);
		assertEquals(2.5, acc.interestEarned(),DOUBLE_DELTA);
	}

	/**
	 * Validate the interest for maxi saving account 
	 * for withdrawal transaction within 10 days
	 */
	@Test
	public void testMaxiSavingsAfterCutoff() {
		Account acc = new Account(AccountType.MAXI_SAVINGS);
		acc.deposit(100, depositDate);
		acc.withdraw(50, withdrawWithin10Days);
		assertEquals(0.005, acc.interestEarned(),DOUBLE_DELTA);		
	}
	/**
	 * Validate the withdraw amount in case of 
	 * request for excess withdrawal than current balance
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testCalculateMaxiSavingsInterest() {
		Account acc = new Account(AccountType.MAXI_SAVINGS);
		acc.deposit(50, depositDate);	
		acc.withdraw(150, withdrawWithin10Days);
	}

	/**
	 * Validate the amount for deposit and withdraw operations 
	 * in case of invalid input
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testForNullAmount() {
		Account acc = new Account(AccountType.MAXI_SAVINGS);
		acc.deposit(0, depositDate);	
		acc.withdraw(0, withdrawWithin10Days);			
	}

	/**
	 * Validate the amount for withdraw operation 
	 * in case of invalid input
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testNegativeWithdrawAmount() {
		Account acc = new Account(AccountType.MAXI_SAVINGS);	
		acc.withdraw(-100, withdrawWithin10Days);				
	}

	/**
	 * Validate the amount for deposit operation 
	 * in case of invalid input
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testNegativeDepositAmount() {
		Account acc = new Account(AccountType.MAXI_SAVINGS);
		acc.deposit(-100, depositDate);				
	}
	
	/**
	 * To Check the calculation of interest earned for no transaction
	 */
	@Test
	public void testNoTransaction() {
		Account acc = new Account(AccountType.MAXI_SAVINGS);
		assertEquals(0.0, acc.interestEarned(),DOUBLE_DELTA);
	}

	/**
	 * To Check the calculation of interest earned for transaction 
	 * without transaction date
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testForNoDate() {
		Account acc = new Account(AccountType.MAXI_SAVINGS);
		acc.deposit(100, null);	
		acc.withdraw(50, null);			
	}
	
	/**
	 * Utility method to get a date object of specific date
	 * @param year
	 * @param day
	 * @param month
	 * @return
	 */
	public Date getDate(int year,int day,int month)
	{
	    cal.set(year,day,month);
	    return cal.getTime();
	}
}
