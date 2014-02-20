package com.abc;

import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * User: mchernyak
 * Date: 2/19/14
 * Time: 5:24 PM
 */
public class AccountTest {
	private static final double DOUBLE_DELTA = 1e-15;

	@Test
	public void testCalculateDailyDateChecking() {
		Account account = new Account(AccountType.CHECKING);

		double balance = 1000.0;
		double interest = account.calculateDailyInterest(balance);
		assertEquals(0.0027397260273973, interest, DOUBLE_DELTA);
		balance += interest;
		interest = account.calculateDailyInterest(balance);
		assertEquals(0.0027397335334960, interest, DOUBLE_DELTA);
		balance += interest;
		interest = account.calculateDailyInterest(balance);
		assertEquals(0.0027397410396152, interest, DOUBLE_DELTA);
	}

	@Test
	public void testCalculateDailyDateSavings() {
		Account account = new Account(AccountType.SAVINGS);

		// from spreadsheet calculation:
		//0.0027397260273972600
		//0.0054794520547945200
		//0.0082191780821917800
		//0.0136986301369863000
		//0.0246575342465753000

		double balance = 1000.0;
		double interest = account.calculateDailyInterest(balance);
		assertEquals(0.0027397260273972600, interest, DOUBLE_DELTA);

		balance = 1500.0;
		interest = account.calculateDailyInterest(balance);
		assertEquals(0.0054794520547945200, interest, DOUBLE_DELTA);

		balance = 3000.0;
		interest = account.calculateDailyInterest(balance);
		assertEquals(0.0136986301369863000, interest, DOUBLE_DELTA);

		balance += interest;
		interest = account.calculateDailyInterest(balance);
		assertEquals(0.0136987051979734000, interest, DOUBLE_DELTA);
	}

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

	@Test
	public void testHadRecentWithdrawals() {
		Account account = new Account(AccountType.CHECKING);
		Date today = new Date();

		Date transactionDate = DateProvider.getInstance().addDays(new Date(), -5);
		account.deposit(100, transactionDate);
		transactionDate = DateProvider.getInstance().addDays(new Date(), -10);
		account.deposit(100, transactionDate);
		transactionDate = DateProvider.getInstance().addDays(new Date(), -13);
		account.withdraw(50, transactionDate);
		assertFalse(account.hadRecentWithdrawals(today));

		transactionDate = DateProvider.getInstance().addDays(new Date(), -10);
		account.withdraw(50, transactionDate);
		assertTrue(account.hadRecentWithdrawals(today));
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
