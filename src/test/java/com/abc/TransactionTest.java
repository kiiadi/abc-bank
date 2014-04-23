package com.abc;

import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;

import org.junit.Test;

public class TransactionTest {
	@Test
	public void transaction() {
		Transaction t = new Transaction(new BigDecimal("5"));
		assertTrue(t instanceof Transaction);
	}
}
