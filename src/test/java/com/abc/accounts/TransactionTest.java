package com.abc.accounts;

import static org.junit.Assert.*;

import java.text.SimpleDateFormat;

import org.junit.Before;
import org.junit.Test;

import com.abc.utils.DateProvider;

/**
 * Test-cases follow the following method-name pattern:
 * methodName_Scenario_ExpectedResult()
 *
 */
public class TransactionTest {
	private Transaction transaction;
	
	@Before
	public void setUp() throws Exception {
		transaction = new Transaction(1000);
	}

	@Test
	public void getAmount_ForNewTransaction_AmountIsSet() {
		assertEquals(transaction.getAmount(), 1000, 0);
	}

	@Test
	public void getTransactionDate_ForNewTransaction_TransactionDateIsSetToday() {
		// get string date-format to compare only the "date" values 
		SimpleDateFormat dateFormat = new SimpleDateFormat("MMM dd yyyy");
		String strDate1 = dateFormat.format(transaction.getTransactionDate());
		
		String strDate2 = dateFormat.format(DateProvider.getInstance().now());
		
		assertTrue(strDate1.equals(strDate2));
	}
	
}
