package com.abc;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Date;

import org.junit.Test;

public class TransactionTest {
    @Test
    public void transaction() {
        Transaction t = new Transaction(5);
        Date presentTime = DateProvider.getInstance().now();
        assertTrue(t instanceof Transaction);
        assertEquals("Amount is wrong in created object.", 5, t.getAmount(), 0.0);
        assertTrue("Time is wrong in created object.", presentTime.getTime() - t.getTransactionDate().getTime() < 100);
    }
}
