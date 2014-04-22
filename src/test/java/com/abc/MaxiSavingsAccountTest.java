package com.abc;

import static org.junit.Assert.assertEquals;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.junit.Before;
import org.junit.Test;

import com.abc.account.Account;
import com.abc.account.MaxiSavingsAccount;

public class MaxiSavingsAccountTest {

	// TODO get rid of this
	private static final double DOUBLE_DELTA = 1e-15;

	private Account maxiSavingsAccount;

	@Before
	public void setUp() throws Exception {
		maxiSavingsAccount = new MaxiSavingsAccount();
	}

	@Test
	public void testDeposit() throws Exception {
		maxiSavingsAccount.deposit(100);
		assertEquals(100, maxiSavingsAccount.sumTransactions(), DOUBLE_DELTA);
	}

	@Test(expected = Exception.class)
	public void testWithdrawalInsufficientFunds() throws Exception {
		assertEquals(0, maxiSavingsAccount.sumTransactions(), DOUBLE_DELTA);
		maxiSavingsAccount.withdraw(1000);
	}

	@Test
	public void testWithdrawal() throws Exception {
		maxiSavingsAccount.deposit(100);
		assertEquals(100, maxiSavingsAccount.sumTransactions(), DOUBLE_DELTA);
		maxiSavingsAccount.withdraw(100);
		assertEquals(0, maxiSavingsAccount.sumTransactions(), DOUBLE_DELTA);
	}

	@Test
	public void testInterestEarnedTransactionsInLast10Days() throws Exception {

		maxiSavingsAccount.deposit(1000);
		maxiSavingsAccount.withdraw(100);

		assertEquals(2, maxiSavingsAccount.getTransactions().size());

		assertEquals(900 * .001, maxiSavingsAccount.interestEarned(),
				DOUBLE_DELTA);

	}

	@Test
	public void testInterestEarnedNoTransactionsInLast10Days() throws Exception {

		maxiSavingsAccount.deposit(1000);
		maxiSavingsAccount.withdraw(100);

		assertEquals(2, maxiSavingsAccount.getTransactions().size());

		Calendar cal = GregorianCalendar.getInstance();
		cal.add(Calendar.DAY_OF_YEAR, -30);
		Date thirtyDaysAgo = cal.getTime();

		// manipulate the transaction date for purposes of test
		for (Transaction transaction : maxiSavingsAccount.getTransactions()) {
			if (transaction.getAmount() < 0) { // a withdrawal
				transaction.setTransactionDate(thirtyDaysAgo);
			}
		}

		assertEquals((900 * .05), maxiSavingsAccount.interestEarned(),
				DOUBLE_DELTA);
	}

}
