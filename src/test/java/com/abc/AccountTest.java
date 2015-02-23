package com.abc;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.Calendar;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class AccountTest {
	protected Account account;
	
	@Before
	public void setUp() throws Exception {
		account = new Account(Account.CHECKING);
	}

	@After
	public void tearDown() throws Exception {
		account = null;
	}

	// Start of tests for static methods
	@Test
	public void testAddDays() {
		Calendar jan15 = TimeTuner.parseISO8601Date("2015-01-15");
		// test forward
		Calendar jan14 = TimeTuner.parseISO8601Date("2015-01-14");
		assertEquals(jan15, Account.addDays(jan14, 1));
		// test backward
		Calendar feb05 = TimeTuner.parseISO8601Date("2015-02-05");
		assertEquals(jan15, Account.addDays(feb05, -21));
		// cross year
		Calendar dec31 = TimeTuner.parseISO8601Date("2014-12-31");
		assertEquals(jan15, Account.addDays(dec31, 15));
		// Before Daylight Saving Time starts at 2am on March 8th in 2015
		Calendar mar08 = TimeTuner.parseISO8601Date("2015-03-08");
		assertEquals(jan15, Account.addDays(mar08, -52));
		// After DST starts
		Calendar mar09 = TimeTuner.parseISO8601Date("2015-03-09");
		assertEquals(mar09, Account.addDays(mar08, 1));
		// Before Daylight Saving Time ends at 2am on Nov 1st in 2015
		Calendar nov01 = TimeTuner.parseISO8601Date("2015-11-01");
		assertEquals(jan15, Account.addDays(nov01, -290));
		// After DST ends
		Calendar nov02 = TimeTuner.parseISO8601Date("2015-11-02");
		assertEquals(nov02, Account.addDays(nov01, 1));
	}

	@Test
	public void testDiffDays() {
		Calendar jan15 = TimeTuner.parseISO8601Date("2015-01-15");
		// test forward
		Calendar jan14 = TimeTuner.parseISO8601Date("2015-01-14");
		assertEquals(jan15, Account.addDays(jan14, 1));
		// test forward
		Calendar jan16 = TimeTuner.parseISO8601Date("2015-01-16");
		assertEquals(jan15, Account.addDays(jan16, -1));
	}

	@Test
	public void testRemoveTime() {
		fail("Not yet implemented");
	}

	@Test
	public void testToCalenderDate() {
		fail("Not yet implemented");
	}

	@Test
	public void testInterestInDateRange() {
		fail("Not yet implemented");
	}
	// End of tests for static methods

	// Start of tests for common methods
	@Test
	public void testAccount() {
		fail("Not yet implemented");
	}

	@Test
	public void testDeposit() {
		fail("Not yet implemented");
	}

	@Test
	public void testWithdraw() {
		fail("Not yet implemented");
	}

	@Test
	public void testSumTransactions() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetAccountType() {
		assertEquals(Account.CHECKING, account.getAccountType());
	}

	// End of tests for common methods

	@Test
	public void testInterestEarned4Checking() {
		TimeTuner timeTuner = new TimeTuner();
		DateProvider.instance = timeTuner;
		timeTuner.now("2013-12-1");
		account.deposit(2000);
		timeTuner.now("2014-12-1");
		assertEquals(2.0, account.interestEarned(), 1E-15);
		account.withdraw(1000);
		timeTuner.now("2015-12-1");
		assertEquals(3.0, account.interestEarned(), 1E-15);
	}

	@Test
	public void testInterestEarned4Saving() {
		account= new Account(Account.SAVINGS);
		TimeTuner timeTuner = TimeTuner.mock();
		timeTuner.now("2013-12-1");
		account.deposit(2000);
		timeTuner.now("2014-12-1");
		assertEquals(3.0, account.interestEarned(), 1E-15);
		account.withdraw(1000);
		timeTuner.now("2015-12-1");
		assertEquals(4.0, account.interestEarned(), 1E-15);
	}

	@Test
	public void testInterestEarned4MaxiSaving() {
		account= new Account(Account.MAXI_SAVINGS);
		TimeTuner timeTuner = TimeTuner.mock();
		timeTuner.now("2013-12-1");
		account.deposit(2000);
		timeTuner.now("2014-12-1");
		assertEquals(100.0, account.interestEarned(), 1E-10);
		account.withdraw(1000);
		timeTuner.now("2014-12-11");
		assertEquals(100.0+10.0/365, account.interestEarned(), 1E-10);
		timeTuner.now("2015-12-1");
		assertEquals(100.0+10.0/365+50.0*355/365, account.interestEarned(), 1E-10);
	}
}
