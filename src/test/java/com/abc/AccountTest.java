package com.abc;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Random;

import org.junit.Before;
import org.junit.Test;

import com.abc.account.Account;

public class AccountTest {

	private MockAccount mockAccount;

	@Before
	public void setUp() throws Exception {
		mockAccount = new MockAccount();
	}

	@Test
	public void testDeposit() throws Exception {
		mockAccount.deposit(new BigDecimal("100.00"));
		assertEquals(new BigDecimal("100.00"),
				mockAccount.sumTransactions());
	}
	@Test(expected = Exception.class)
	public void testWithdrawalInsufficientFunds() throws Exception {
		assertEquals(new BigDecimal("0.00"),
				mockAccount.sumTransactions());
		mockAccount.withdraw(new BigDecimal("1000.00"));
	}
	
	@Test
	public void testWithdrawal() throws Exception {
		mockAccount.deposit(new BigDecimal("100.00"));
		assertEquals(new BigDecimal("100.00"),
				mockAccount.sumTransactions());

		mockAccount.withdraw(new BigDecimal("100.00"));
		assertEquals(new BigDecimal("0.00"),
				mockAccount.sumTransactions());
	}


	// TODO
	public void testSumTransactions() {
		fail("Not yet implemented");
	}

	@Test
	public void testSortTransactionsAscending() {

		// create transactions
		mockAccount.deposit(new BigDecimal("25.00"));
		mockAccount.deposit(new BigDecimal("25.00"));
		mockAccount.deposit(new BigDecimal("25.00"));
		mockAccount.deposit(new BigDecimal("25.00"));

		// populate random dates
		for (Transaction transaction : mockAccount.getTransactions()) {

			Calendar transactionDate = Calendar.getInstance();
			transactionDate.set(Calendar.DAY_OF_MONTH,
					new Random().nextInt(31) + 1);

			transaction.setTransactionDate(transactionDate.getTime());
		}

		mockAccount.sortTransactionsAscending();

		// assert ordering
		Transaction previous = null;

		for (Transaction transaction : mockAccount.getTransactions()) {

			if (previous != null) {
				assertTrue(previous.getTransactionDate().compareTo(
						transaction.getTransactionDate()) <= 0);
			}

			previous = transaction;
		}

	}

	@Test
	public void testGetDailyBalances() throws Exception {

		mockAccount.deposit(new BigDecimal("100.00"));
		mockAccount.withdraw(new BigDecimal("100.00"));
		mockAccount.deposit(new BigDecimal("100.00"));

		mockAccount
				.getTransactions()
				.get(0)
				.setTransactionDate(
						new SimpleDateFormat("yyyyMMdd").parse("20140401"));

		mockAccount
				.getTransactions()
				.get(1)
				.setTransactionDate(
						new SimpleDateFormat("yyyyMMdd").parse("20140415"));

		mockAccount
				.getTransactions()
				.get(2)
				.setTransactionDate(
						new SimpleDateFormat("yyyyMMdd").parse("20140420"));

		ArrayList<BigDecimal> dailyBalances = mockAccount.getDailyBalances();
		assertNotNull(dailyBalances);
		assertFalse(dailyBalances.isEmpty());

		int i = 1;
		for (BigDecimal dailyBalance : dailyBalances) {
			System.out.println(i++ + " " + dailyBalance);
		}

	}

	public class MockAccount extends Account {

		@Override
		public BigDecimal interestEarned() {
			// TODO Auto-generated method stub
			return null;
		}

	}
}
