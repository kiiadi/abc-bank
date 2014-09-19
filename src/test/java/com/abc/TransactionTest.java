package com.abc;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.Before;
import org.junit.Test;

public class TransactionTest {

	public TransactionTest() {
	}

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testPrintTransactionDeposit() {
		// STEP 1: Setup Input Param
		double amount = 1000.0d;

		// STEP 2: Construct expected output
		StringBuilder statementBuilder = new StringBuilder();
		statementBuilder.append("  ").append(TransactionType.DEPOSIT)
				.append(" ").append(Utility.toDollars(amount)).append("\n");

		String expectedResult = statementBuilder.toString();

		// STEP 3: Make a unit test call to determine the actual output
		Transaction transact = new Transaction(amount);
		String actualResult = transact.printTransaction();

		// STEP 4: Compare the results
		assertEquals(expectedResult, actualResult);

	}

	@Test
	public void testPrintTransactionWithdrawal() {
		// STEP 1: Setup Input Param
		double amount = -1000.0d;

		// STEP 2: Construct expected output
		StringBuilder statementBuilder = new StringBuilder();
		statementBuilder.append("  ").append(TransactionType.WITHDRAWAL)
				.append(" ").append(Utility.toDollars(amount)).append("\n");

		String expectedResult = statementBuilder.toString();

		// STEP 3: Make a unit test call to determine the actual output
		Transaction transact = new Transaction(amount);
		String actualResult = transact.printTransaction();

		// STEP 4: Compare the results
		assertEquals(expectedResult, actualResult);

	}

	@Test
	public void testPrintTransactionZeroAmount() {
		// STEP 1: Setup Input Param
		double amount = 0.0d;

		String expectedResult = BankConstants.INVALID_AMOUNT;
		String actualResult = null;
		// STEP 3: Make a unit test call to determine the actual output
		Transaction transact = null;
		try {
			transact = new Transaction(amount);
		} catch (Exception e) {
			actualResult = e.getMessage();
		}

		// STEP 4: Compare the results
		assertNull(transact);
		assertEquals(expectedResult, actualResult);
	}

	@Test
	public void testTransactionDate() {

		// STEP 1: Setup Input Param
		Double amount = 990.0d;

		// STEP 2: Create a transaction object
		Transaction transact = new Transaction(amount);

		// STEP 3: check attributes if they are all valid
		assertNotNull(transact);
		assertNotNull(transact.getTransactionDate());
		assertNotNull(transact.getTransactType());

		Double actual = transact.getAmount();
		assertEquals(actual, amount);
	}

}
