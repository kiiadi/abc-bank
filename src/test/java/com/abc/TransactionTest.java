package com.abc;

import org.junit.Test;

import java.util.Date;
import java.util.TreeSet;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class TransactionTest {

	/**
	 * Test Transaction comparator method ensuring chronological ordering
	 */
	@Test
	public void testTransactionComparator() {
		Date d0 = DateUtil.dateFromYMD(2014, 1, 1);
		Date d1 = DateUtil.dateFromYYYYMMDDString("20140101");
		Date d2 = DateUtil.dateFromYMD(2014, 1, 2);
		Date d3 = DateUtil.dateFromYMD(2014, 2, 20);

		Transaction t0 = new Transaction(5, d0);
		Transaction t1 = new Transaction(5, d1);
		Transaction t2 = new Transaction(5, d2);
		Transaction t3 = new Transaction(5, d3);
		Transaction t4 = new Transaction(-1, d3);

		int compare = t0.compareTo(t0);
		assertTrue(compare == 0);

		compare = t0.compareTo(t1);
		assertTrue(compare <= 0);

		compare = t1.compareTo(t2);
		assertTrue(compare < 0);

		compare = t3.compareTo(t2);
		assertTrue(compare > 0);

		compare = t4.compareTo(t3);
		assertTrue(compare > 0);
	}


	/**
	 * test that transaction set is presenting transaction in a chronological order in a TreeSet
	 */
	@Test
	public void testTransactionSet() {
		TreeSet<Transaction> transactions = new TreeSet<>();

		Transaction t1 = new Transaction(5, DateUtil.dateFromYYYYMMDDString("20140101"));
		Transaction t2 = new Transaction(5, DateUtil.dateFromYYYYMMDDString("20140102"));
		Transaction t3 = new Transaction(-3, DateUtil.dateFromYYYYMMDDString("20140103"));
		Transaction t4 = new Transaction(-4, DateUtil.dateFromYYYYMMDDString("20140104"));
		Transaction t5 = new Transaction(9, DateUtil.dateFromYYYYMMDDString("20140102"));
		Transaction t6 = new Transaction(8, DateUtil.dateFromYYYYMMDDString("20140102"));

		transactions.add(t1);
		transactions.add(t2);
		transactions.add(t3);
		transactions.add(t4);
		transactions.add(t5);
		transactions.add(t6);

		Transaction firstTransaction = transactions.first();
		Transaction lastTransaction = transactions.last();

		assertEquals(t1, firstTransaction);
		assertEquals(t4, lastTransaction);
	}
}
