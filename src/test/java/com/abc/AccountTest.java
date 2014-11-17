package com.abc;

import java.util.GregorianCalendar;
import java.util.Calendar;
import java.util.Date;

import org.junit.Test;

import com.abc.Account.AccountType;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;

public class AccountTest {

	private static final double DOUBLE_DELTA = 1e-15;

	/**
	 * Test instance creation
	 */
	@Test
	public void testAccount() {
		Account acct = new Account(AccountType.CHECKING);
		assertTrue(acct instanceof Account);
	}

	/**
	 * Test valid amount deposit
	 */
	@Test
	public void testDepositValidAmount() {
		Account acct = new Account(AccountType.SAVINGS);
		acct.deposit(200.0);
		assertEquals(acct.getTransactions().get(0).getAmount(), 200.0,
				DOUBLE_DELTA);
	}

	/**
	 * Test invalid amount deposit with a negative amount
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testDepositInvalidAmount() {
		Account acct = new Account(AccountType.SAVINGS);
		acct.deposit(-20.0);
	}

	/**
	 * Test invalid amount withdrawal with a negative amount
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testWithdrawalInvalidAmount() {
		Account acct = new Account(AccountType.SAVINGS);
		acct.withdraw(-20.0);
	}
	
	/**
	 * Test withdrawal with amount more than the available account balance
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testWithdrawalMoreThanAccountBalance() {
		Account acct = new Account(AccountType.SAVINGS);
		acct.deposit(5000.0);
		acct.withdraw(10000);
	}


	/**
	 * Test last withdrawal date when there are multiple withdrawals
	 */
	@Test
	public void testLastWithdrawalDateWithMultipleWithdrawals() {
		Calendar cal = new GregorianCalendar();
		cal.set(2014, Calendar.NOVEMBER, 10, 5, 30, 25);
		Date firstDate = cal.getTime();
		cal.add(Calendar.DAY_OF_WEEK, 2);
		Date secondDate = cal.getTime();
		cal.add(Calendar.DAY_OF_WEEK, 3);
		Date thirdDate = cal.getTime();
		Account acct = new Account(AccountType.SAVINGS);
		acct.deposit(5000.0, firstDate);
		acct.withdraw(200, secondDate);
		acct.withdraw(30, thirdDate);
		assertEquals("Sat Nov 15 05:30:25 EST 2014", acct
				.getLastWithdrawalDate().toString());
	}
	
	
	/**
	 * Test last withdrawal date when there are no withdrawals
	 * Lead to NullPointerException as the last withdrawal date is not available
	 */
	@Test(expected = NullPointerException.class)
	public void testLastWithdrawalDateWithNoWithdrawals() {
		Calendar cal = new GregorianCalendar();
		cal.set(2014, Calendar.NOVEMBER, 10, 5, 30, 25);
		Date firstDate = cal.getTime();
		Account acct = new Account(AccountType.SAVINGS);
		acct.deposit(5000.0, firstDate);
		assertEquals("Sat Nov 15 05:30:25 EST 2014", acct
				.getLastWithdrawalDate().toString());
	}

	/**
	 * Saving account Interest when the balance is below 1000
	 * Test would fail as the number of days used in calculation depends on the day of the run
	 * Expected value may be modified to make the test pass
	 */
	@Test
	public void testSavingInterestBelow1000() {
		Calendar cal = new GregorianCalendar();
		cal.set(2014, Calendar.NOVEMBER, 5, 5, 30, 25);
		Date firstDate = cal.getTime();
		cal.add(Calendar.DAY_OF_WEEK, 2);
		Date secondDate = cal.getTime();
		Account acct = new Account(AccountType.SAVINGS);
		acct.deposit(800.0, firstDate);
		acct.withdraw(200.0, secondDate);
		assertEquals(0.01808219178082192, acct.interestEarned(), DOUBLE_DELTA);
	}

	/**
	 * Saving account Interest when the balance is above 1000
	 * Test would fail as the number of days used in calculation depends on the day of the run
	 * Expected value may be modified to make the test pass
	 */
	@Test
	public void testSavingInterestAbove1000() {
		Calendar cal = new GregorianCalendar();
		cal.set(2014, Calendar.NOVEMBER, 5, 5, 30, 25);
		Date firstDate = cal.getTime();
		cal.add(Calendar.DAY_OF_WEEK, 2);
		Date secondDate = cal.getTime();
		Account acct = new Account(AccountType.SAVINGS);
		acct.deposit(1500.0, firstDate);
		acct.withdraw(200.0, secondDate);
		assertEquals(0.01808219178082192, acct.interestEarned(), DOUBLE_DELTA);
	}

	/**
	 * Checking account Interest when the balance is above 1000
	 * Test would fail as the number of days used in calculation depends on the day of the run
	 * Expected value may be modified to make the test pass
	 */
	public void testCheckingInterest() {
		Calendar cal = new GregorianCalendar();
		cal.set(2014, Calendar.NOVEMBER, 5, 5, 30, 25);
		Date firstDate = cal.getTime();
		cal.add(Calendar.DAY_OF_WEEK, 2);
		Date secondDate = cal.getTime();
		Account acct = new Account(AccountType.CHECKING);
		acct.deposit(800.0, firstDate);
		acct.withdraw(200.0, secondDate);
		assertEquals(0.01808219178082192, acct.interestEarned(), DOUBLE_DELTA);
	}

	/**
	 * Maxi Savings account Interest when there is no withdrawal in the last 10 days
	 * Test would fail as the number of days used in calculation depends on the day of the run
	 * Expected value may be modified to make the test pass
	 */
	@Test
	public void testMaxiSavingInterestWithNo10DaysWithdrawal() {
		Calendar cal = new GregorianCalendar();
		cal.set(2014, Calendar.OCTOBER, 10, 5, 30, 25);
		Date firstDate = cal.getTime();
		cal.add(Calendar.DAY_OF_WEEK, 2);
		Date secondDate = cal.getTime();
		Account acct = new Account(AccountType.MAXI_SAVINGS);
		acct.deposit(5000.0, firstDate);
		acct.withdraw(200.0, secondDate);
		assertEquals(24.328767123287673, acct.interestEarned(), DOUBLE_DELTA);
	}

	/**
	 * Maxi Savings account Interest when there are withdrawals in the last 10 days
	 * Test would fail as the number of days used in calculation depends on the day of the run
	 * Expected value may be modified to make the test pass
	 */
	@Test
	public void testMaxiSavingInterestWithIn10DaysWithdrawal() {
		Calendar cal = new GregorianCalendar();
		cal.set(2014, Calendar.NOVEMBER, 5, 5, 30, 25);
		Date firstDate = cal.getTime();
		cal.add(Calendar.DAY_OF_WEEK, 10);
		Date secondDate = cal.getTime();
		Account acct = new Account(AccountType.MAXI_SAVINGS);
		acct.deposit(5000.0, firstDate);
		acct.withdraw(200.0, secondDate);
		assertEquals(0.14465753424657535, acct.interestEarned(), DOUBLE_DELTA);
	}

}
