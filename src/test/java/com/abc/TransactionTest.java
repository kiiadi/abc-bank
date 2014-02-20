package com.abc;

import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.assertTrue;

public class TransactionTest {

    @Test
    public void transaction() {
        Transaction t = new Transaction(5);
        assertTrue(t instanceof Transaction);
    }

	@Test
	public void testTransactionComparator() {
		Date d0 = DateProvider.getInstance().getDateByYMD(2014,1,1);
		Date d1 = DateProvider.getInstance().dateFromString("20140101");
		Date d2 = DateProvider.getInstance().getDateByYMD(2014, 1, 2);
		Date d3 = DateProvider.getInstance().getDateByYMD(2014,2,20);

		Transaction t0 = new Transaction(5,d0);
		Transaction t1 = new Transaction(5,d1);
		Transaction t2 = new Transaction(5,d2);
		Transaction t3 = new Transaction(5,d3);

		int compare = t0.compareTo(t0);
		assertTrue(compare == 0);

		compare = t0.compareTo(t1);
		assertTrue(compare == 0);

		compare = t1.compareTo(t2);
		assertTrue(compare < 0);

		compare = t3.compareTo(t2);
		assertTrue(compare > 0);
	}
}
