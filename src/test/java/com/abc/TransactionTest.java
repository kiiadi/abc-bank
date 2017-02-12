package com.abc;

import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class TransactionTest extends BaseTestFixture {
    @Test
    public void transaction() {
        Transaction t = new Transaction(5);
        assertTrue(t instanceof Transaction);
        assertTrue(Math.abs(t.getTransactionDate().getTime() - System.currentTimeMillis())<MAX_MS_DELAY_4_TEST ) ;
    }
}
