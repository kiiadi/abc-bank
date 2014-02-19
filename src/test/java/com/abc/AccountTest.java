package com.abc;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * User: mchernyak
 * Date: 2/19/14
 * Time: 5:24 PM
 */
public class AccountTest {
	private static final double DOUBLE_DELTA = 1e-15;

	@Test(expected = java.lang.IllegalArgumentException.class)
	public void testIllegalDeposit() {
		Account account = new Account(AccountType.CHECKING);
		account.deposit(-100);
	}

	@Test(expected = java.lang.IllegalArgumentException.class)
	public void testIllegalWithdrawal() {
		Account account = new Account(AccountType.SAVINGS);
		account.withdraw(-100);
	}

	/**
	 * Test Checking Account calculation, per specification:
	 * Checking accounts have a flat rate of 0.1%
	 * Maxi-Savings accounts have a rate of 2% for the first $1,000 then 5% for the next $1,000 then 10%
	 */
	@Test
	public void testCalculateCheckingInterest() {
		Account account = new Account(AccountType.CHECKING);
		account.deposit(100);
		account.deposit(100);
		account.deposit(100);
		assertEquals(0.3, account.interestEarned(), DOUBLE_DELTA);
		account.deposit(700);
		assertEquals(1.0, account.interestEarned(), DOUBLE_DELTA);
	}

	/**
	 * Test Savings Account calculation, per specification:
	 * Savings accounts have a rate of 0.1% for the first $1,000 then 0.2%
	 */
	@Test
	public void testCalculateSavingsInterest() {
		Account account = new Account(AccountType.SAVINGS);
		account.deposit(500);
		account.deposit(500);
		assertEquals(1.0, account.interestEarned(), DOUBLE_DELTA);

		account.deposit(500);
		assertEquals(2.0, account.interestEarned(), DOUBLE_DELTA);

		account.deposit(250);
		assertEquals(2.5, account.interestEarned(), DOUBLE_DELTA);
	}

	/**
	 * Test Maxi-Savings Account calculation, per specification:
	 * Maxi-Savings accounts have a rate of 2% for the first $1,000 then 5% for the next $1,000 then 10%
	 */
	@Test
	public void testCalculateMaxiSavingsInterest() {
		Account account = new Account(AccountType.MAXI_SAVINGS);

		account.deposit(500);
		assertEquals(10.0, account.interestEarned(), DOUBLE_DELTA);

		account.deposit(1000);
		// bal=1500, interest: 1000 * 0.02 + 500 * 0.05 = 20 + 25
		assertEquals(45.0, account.interestEarned(), DOUBLE_DELTA);

		account.deposit(1000);
		// bal=2500, interest: 1000 * 0.02 + 1000 * 0.05 + 500 * 0.1 = 20 + 50 + 50
		assertEquals(120.0, account.interestEarned(), DOUBLE_DELTA);
	}
}
