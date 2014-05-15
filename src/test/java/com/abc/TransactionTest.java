package com.abc;

import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class TransactionTest {

	@Test
    public void transaction() {
        Transaction t = new Transaction(5, TransactionType.CUSTOMER_DEPOSIT);
        assertTrue(t instanceof Transaction);
    }
	
}
