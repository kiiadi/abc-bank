package com.abc;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class TransactionTypeTest {

	public TransactionTypeTest() {
	}

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testGetDescription() {
		assertEquals("deposit", TransactionType.DEPOSIT.getDescription());
		assertEquals("withdrawal", TransactionType.WITHDRAWAL.getDescription());
	}

	@Test
	public void testGetCode() {
		assertEquals(0, TransactionType.DEPOSIT.getCode());
		assertEquals(1, TransactionType.WITHDRAWAL.getCode());
	}

}
