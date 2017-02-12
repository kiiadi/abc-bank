package com.abc;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class TransactionTest extends BaseTestFixture {
    @Test
    public void transaction() {
        Transaction t = new Transaction(5);
        assertEquals(5,t.getAmount(),DOUBLE_DELTA);
        assertTrue(Math.abs(t.getTransactionDate().getTime() - System.currentTimeMillis())<MAX_MS_DELAY_4_TEST ) ;
    }
}
