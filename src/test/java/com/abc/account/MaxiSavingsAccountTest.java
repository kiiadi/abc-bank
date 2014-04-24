package com.abc.account;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.junit.Before;
import org.junit.Test;

import com.abc.Transaction;
import com.abc.account.Account;
import com.abc.account.MaxiSavingsAccount;

public class MaxiSavingsAccountTest {

	private Account maxiSavingsAccount;

	@Before
	public void setUp() throws Exception {
		maxiSavingsAccount = new MaxiSavingsAccount();
	}

	@Test
	public void testInterestEarnedTransactionsInLast10Days() throws Exception {

		maxiSavingsAccount.deposit(new BigDecimal("1000.00"));
		maxiSavingsAccount.withdraw(new BigDecimal("100.00"));

		assertEquals(2, maxiSavingsAccount.getTransactions().size());

		assertEquals(new BigDecimal("900.00").multiply(new BigDecimal(".001")),
				maxiSavingsAccount.interestEarned());

	}

	@Test
	public void testInterestEarnedNoTransactionsInLast10Days() throws Exception {

		maxiSavingsAccount.deposit(new BigDecimal("1000.00"));
		maxiSavingsAccount.withdraw(new BigDecimal("100.00"));

		assertEquals(2, maxiSavingsAccount.getTransactions().size());

		Calendar cal = GregorianCalendar.getInstance();
		cal.add(Calendar.DAY_OF_YEAR, -30);
		Date thirtyDaysAgo = cal.getTime();

		// manipulate the transaction date for purposes of test
		for (Transaction transaction : maxiSavingsAccount.getTransactions()) {
			if (transaction.getAmount().compareTo(new BigDecimal("0")) < 0) { // withdrawal
				transaction.setTransactionDate(thirtyDaysAgo);
			}
		}

		assertEquals(new BigDecimal("900.00").multiply(new BigDecimal(".05")),
				maxiSavingsAccount.interestEarned());
	}

}
