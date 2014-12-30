package com.abc;

import org.junit.Test;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class TransactionTest {
    private static final double DOUBLE_DELTA = 1e-15;

	@Test
    public void transaction() {
        Transaction t = new Transaction(5);
        assertTrue(t instanceof Transaction);
        assertEquals(5, t.getAmount(), DOUBLE_DELTA);
        assertNotNull("Date cannot be null", t.getTransactionDate());
    }
}
