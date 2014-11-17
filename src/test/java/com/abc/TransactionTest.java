package com.abc;

import java.util.GregorianCalendar;
import java.util.Calendar;
import java.util.Date;
import org.junit.Test;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;

public class TransactionTest {

	/**
	 * Test instance creation
	 */
	@Test
	public void testTransaction() {
		Transaction t = new Transaction(5);
		assertTrue(t instanceof Transaction);
	}

	/**
	 * Test transaction date when a fixed date is passed
	 */
	@Test
	public void testTransactionDate() {
		Calendar cal = new GregorianCalendar();
		cal.set(2014, Calendar.NOVEMBER, 15, 5, 30, 25);
		Date transactionDate = cal.getTime();
		Transaction t = new Transaction(100.0, transactionDate);
		assertEquals("Sat Nov 15 05:30:25 EST 2014", t.getTransactionDate()
				.toString());
	}

}
