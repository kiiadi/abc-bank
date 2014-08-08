package com.abc;

import static org.junit.Assert.*;

import org.junit.Test;

import java.util.Date;

/**
 * Unit test for {@link  Account}
 */
public class AccountTest {
	
	/**
	 * Test account creating.
	 */
	@Test
	public void accountCreation() {
		Account a = new Account(AccountType.SAVINGS);
		assertTrue(a instanceof Account);
        assertTrue(a.getAccountType() == AccountType.SAVINGS);
	}
	
	/**
	 * Test calculation of total account balance.
	 */
	@Test
	public void sumTransactions() {
		Account a = new Account(AccountType.CHECKING);
		a.deposit(50.0);
		a.withdraw(20.0);
		a.deposit(70.0);
		a.withdraw(30.0);
		assertTrue(a.numberOfTransactions() == 4);
		assertTrue(a.sumTransactions() == 70.0);		
	}
	
	/**
	 * Test  exceptions raising for wrong withdraw/deposit amount,
	 */
	@Test(expected=IllegalArgumentException.class)
	public void exeptions() {
		Account a = new Account(AccountType.CHECKING);
		a.withdraw(-20.0);
		a.deposit(-10.0);
		a.deposit(50.0);
		a.withdraw(70.0);
	}
	
	/**
	 * Test calculation of earned interest for checking account.
	 */
	@Test
	public void interestEarnedChecking() {
		Account a = new Account(AccountType.CHECKING);
		// Account has a flat rate of 0.1%.
		double rate = InterestRates.CHECKING_RATE.getVal() / 365;
		
		// Test calculation for 1 month.			
		// We expect amount * 31 days * daily rate.
		Date d1 = Account.getDateFromString("January 1, 2014");
		Date d2 = Account.getDateFromString("February 1, 2014");		
		a.deposit(1000.0, d1);	
		double expect = 1000.0 * 31 * rate;
		double earned = a.interestEarned(d2);		
		assertTrue(earned == expect);
		
		/*
		 *  Test calculation for 3 months with different deposit/withdraw amounts and dates.
		 *  We expect result as sum of the previous January and new February and March:
		 *  - for Feb after withdraw: 500 * 28 days * daily rate;
		 *  - for Mar after deposit: 700 * 31 days * daily rate;
		 */
		Date d3 = Account.getDateFromString("March 1, 2014");
		Date d4 = Account.getDateFromString("April 1, 2014");			
		a.withdraw(500.0, d2);	
		a.deposit(200.0, d3);
		expect += 500.0 * 28 * rate;
		expect += 700.0 * 31 * rate;	
		earned = a.interestEarned(d4);
		assertTrue(earned == expect);
	}
	
	/**
	 * Test calculation of earned interest for checking account.
	 */
	@Test
	public void interestEarnedSaving() {
		Account a = new Account(AccountType.SAVINGS);
		// Account has a rate of 0.1% for the first $1,000 then 0.2%.
		double rate = InterestRates.CHECKING_RATE.getVal() / 365;
		double rateMax = InterestRates.SAVING_RATE.getVal() / 365;
		
		// Test calculation for 1 month		
		// We expect amount * 31 days * daily rate
		Date d1 = Account.getDateFromString("January 1, 2014");
		Date d2 = Account.getDateFromString("February 1, 2014");		
		a.deposit(1000.0, d1);
		double expect = 1000.0 * 31 * rate;
		double earned = a.interestEarned(d2);		
		assertTrue(earned == expect);
		
		/* 
		 * Test calculation for 2 months.
		 * We added some amount and now balance is more than 1000.
		 * Earning is calculated with increased rate for balance above $1,000.
		 * We expect result as sum of the previous January and new February:
		 * - for Feb (first 1000): 1000 * 28 days * daily rate;
		 * - for Feb (above 1000): 500 * 31 days * daily rate max;
		 */
		a.deposit(500.0, d2);
		Date d3 = Account.getDateFromString("March 1, 2014");	
		expect += 1000.0 * 28 * rate;
		expect += 500.0 * 28 * rateMax;	
		earned = a.interestEarned(d3);		
		assertTrue(earned == expect);
		
		/* 
		 * Test calculation for 3 months.
		 * We withdraw some amount and now balance is less than $1000.
		 * We expect result as sum of the previous January and February plus new March:
		 * - for Mar (first 1000): 700 * 28 days * daily rate;	
		 */	
		a.withdraw(800.0, d3);
		Date d4 = Account.getDateFromString("April 1, 2014");
		expect += 700.0 * 31 * rate;
		earned = a.interestEarned(d4);		
		assertTrue(earned == expect);
	}
	
	/**
	 * Test calculation of earned interest for maxi checking account.
	 */
	@Test
	public void interestEarnedMaxiSaving() {
		Account a = new Account(AccountType.MAXI_SAVINGS);
		// Account has an interest rate of 5% assuming no withdrawals in the past 10 days
		// otherwise 0.1%.
		double rate = InterestRates.CHECKING_RATE.getVal() / 365;
		double rateMax = InterestRates.MAXI_SAVING_RATE.getVal() / 365;
		
		// Test calculation for 1 month which is more than 10 days.		
		// We expect amount * 31 days * daily rate max.
		Date d1 = Account.getDateFromString("January 1, 2014");
		Date d2 = Account.getDateFromString("February 1, 2014");		
		a.deposit(1000.0, d1);
		double expect = 1000.0 * 31 * rateMax;
		double earned = a.interestEarned(d2);		
		assertTrue(earned == expect);
		
		/* Test calculation for mixed periods.
		 * We added some amount and hold it less than 10 days.
		 * We expect result as sum of the previous January and new February periods:
		 * - for Feb (after deposit): 1500 * 5 days * daily rate;
		 * - for Feb (after withdraw): 700 * 9 days * daily rate;
		 */
		Date d3 = Account.getDateFromString("February 6, 2014");			
		a.deposit(500.0, d2);
		a.withdraw(800.0, d3);
		Date d4 = Account.getDateFromString("February 15, 2014");
		expect += 1500.0 * 5 * rate;
		expect+= 700.0 * 9 * rate;
		earned = a.interestEarned(d4);		
		assertTrue(earned == expect);
		
		/*
		 *  Test calculation for mixed periods.
		 *  We added new amount and hold it more than 10 days.
		 *  All previous periods must be recalculated with increased interest rate.
		 *  We expect result as sum of the following:
		 *  - for Jan: 1000 * 31 days * daily rate max;
		 *  - for Feb (after deposit): 1500 * 5 days * daily rate max;
		 *  - for Feb (after withdraw): 700 * 9 days * daily rate max;
		 *  - for Feb (after deposit): 900 * 14 days * daily rate max max;
		 */
		a.deposit(200.0, d4);
		Date d5 = Account.getDateFromString("March 1, 2014");	
		expect = 1000.0 * 31 * rateMax;
		expect += 1500.0 * 5 * rateMax;
		expect += 700.0 * 9 * rateMax;
		expect += 900.0 * 14 * rateMax;
		earned = a.interestEarned(d5);		
		assertTrue(earned == expect);
	}
	
	/**
	 * Test account statement generation.	
	 */
    @Test
    public void statement(){
    	Account a = new Account(AccountType.CHECKING);
    	Date d1 = Account.getDateFromString("January 1, 2014");
		Date d2 = Account.getDateFromString("February 1, 2014");		
    	a.deposit(1500.0, d1);
		a.withdraw(800.0, d2);
		
		StringBuilder sb = new StringBuilder("Checking Account (2 transactions)\n");
		sb.append( "  01/01/2014 deposit $1,500.00\n");
		sb.append( "  02/01/2014 withdrawal $800.00\n");
        sb.append("Total $700.00\n");
		
		assertTrue(sb.toString().equals(a.getStatement()));
    }
	


}
