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
	private static final double DOUBLE_DELTA = 1e-10;

	@Test
	public void testCalculateAccruedInterestMaxiSavings() {
		Account account = new Account(AccountType.MAXI_SAVINGS);
		Date today = new Date();

		Date transactionDate = DateProvider.getInstance().addDays(new Date(), -10);

		account.deposit(2000, transactionDate);

		double accruedInterest = account.calculateBalanceAndInterest(today);
		assertEquals(2.7414155166900000, accruedInterest, DOUBLE_DELTA);

		transactionDate = DateProvider.getInstance().addDays(new Date(), -5);
		account.deposit(1000, transactionDate);

		accruedInterest = account.calculateBalanceAndInterest(today);
		assertEquals(3.4265347017200000, accruedInterest, DOUBLE_DELTA);

		account = new Account(AccountType.MAXI_SAVINGS);
		transactionDate = DateProvider.getInstance().addDays(new Date(), -10);
		account.deposit(5000, transactionDate);

		accruedInterest = account.calculateBalanceAndInterest(today);
		assertEquals(6.8535387917300000, accruedInterest, DOUBLE_DELTA);

		transactionDate = DateProvider.getInstance().addDays(new Date(), -5);
		try {
			account.withdraw(1000, transactionDate);
		} catch (AccountModificationException e) {
			e.printStackTrace();
		}

		accruedInterest = account.calculateBalanceAndInterest(today);
		assertEquals(3.48043767214404000000, accruedInterest, DOUBLE_DELTA);
	}

	@Test
	public void testCalculateAccruedInterestSavings() {
		Account account = new Account(AccountType.SAVINGS);
		Date today = new Date();

		Date transactionDate = DateProvider.getInstance().addDays(new Date(), -10);
		account.deposit(2000, transactionDate);

		double accruedInterest = account.calculateBalanceAndInterest(today);
		assertEquals(0.0821938075000000, accruedInterest, DOUBLE_DELTA);

		transactionDate = DateProvider.getInstance().addDays(new Date(), -5);
		account.deposit(1000, transactionDate);

		accruedInterest = account.calculateBalanceAndInterest(today);
		assertEquals(0.1095913680200000, accruedInterest, DOUBLE_DELTA);
	}


	@Test
	public void testCalculateAccruedInterestChecking() {
		Account account = new Account(AccountType.CHECKING);
		Date today = new Date();

		Date transactionDate = DateProvider.getInstance().addDays(new Date(), -10);
		account.deposit(1000, transactionDate);

		double accruedInterest = account.calculateBalanceAndInterest(today);
		assertEquals(0.0273975980500, accruedInterest, DOUBLE_DELTA);

		transactionDate = DateProvider.getInstance().addDays(new Date(), -5);
		account.deposit(500, transactionDate);

		accruedInterest = account.calculateBalanceAndInterest(today);
		assertEquals(0.03424695065000, accruedInterest, DOUBLE_DELTA);
	}

	@Test
	public void testCalculateDailyRateMaxiSavings() {

		Account account = new Account(AccountType.MAXI_SAVINGS);
		Date today = new Date();

		Date transactionDate = DateProvider.getInstance().addDays(today, -9);
		account.deposit(1000, transactionDate);
		double balance = 1000.0;
		double interest = account.calculateDailyInterest(balance, today);
		assertEquals(0.1369863013698630, interest, DOUBLE_DELTA);
		balance = 3000.0;
		interest = account.calculateDailyInterest(balance, today);
		assertEquals(0.4109589041095890, interest, DOUBLE_DELTA);

		balance = 1000.0;
		try {
			account.withdraw(10, transactionDate);
		} catch (AccountModificationException e) {
			e.printStackTrace();
		}
		interest = account.calculateDailyInterest(balance, today);
		assertEquals(0.0027397260273973, interest, DOUBLE_DELTA);

		balance = 3000.0;
		interest = account.calculateDailyInterest(balance, today);
		assertEquals(0.0082191780821918, interest, DOUBLE_DELTA);
	}

	@Test
	public void testCalculateDailyRateChecking() {
		Account account = new Account(AccountType.CHECKING);

		double balance = 1000.0;
		double interest = account.calculateDailyInterest(balance, null);
		assertEquals(0.0027397260273973, interest, DOUBLE_DELTA);
		balance += interest;
		interest = account.calculateDailyInterest(balance, null);
		assertEquals(0.0027397335334960, interest, DOUBLE_DELTA);
		balance += interest;
		interest = account.calculateDailyInterest(balance, null);
		assertEquals(0.0027397410396152, interest, DOUBLE_DELTA);
	}

	@Test
	public void testCalculateDailyRateSavings() {
		Account account = new Account(AccountType.SAVINGS);

		double balance = 1000.0;
		double interest = account.calculateDailyInterest(balance, null);
		assertEquals(0.0027397260273972600, interest, DOUBLE_DELTA);

		balance = 1500.0;
		interest = account.calculateDailyInterest(balance, null);
		assertEquals(0.0054794520547945200, interest, DOUBLE_DELTA);

		balance = 3000.0;
		interest = account.calculateDailyInterest(balance, null);
		assertEquals(0.0136986301369863000, interest, DOUBLE_DELTA);

		balance += interest;
		interest = account.calculateDailyInterest(balance, null);
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
		try {
			account.withdraw(-100);
		} catch (AccountModificationException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testHadRecentWithdrawals() {
		Account account = new Account(AccountType.CHECKING);
		Date today = new Date();

		Date transactionDate = DateProvider.getInstance().addDays(new Date(), -15);
		account.deposit(200, transactionDate);

		transactionDate = DateProvider.getInstance().addDays(new Date(), -10);
        account.deposit(100, transactionDate);

		transactionDate = DateProvider.getInstance().addDays(new Date(), -13);

		try {
			account.withdraw(50, transactionDate);
		} catch (AccountModificationException e) {
			e.printStackTrace();
		}
		assertFalse(account.hadRecentWithdrawals(today));

		transactionDate = DateProvider.getInstance().addDays(new Date(), -9);
		try {
			account.withdraw(50, transactionDate);
		} catch (AccountModificationException e) {
			e.printStackTrace();
		}
		assertTrue(account.hadRecentWithdrawals(today));
	}
}
