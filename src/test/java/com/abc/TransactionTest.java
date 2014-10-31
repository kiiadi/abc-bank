package com.abc;

import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class TransactionTest {
    @Test
    public void transaction() {
        TransactionOld t = new TransactionOld(5);
        assertTrue(t instanceof TransactionOld);
    }
}
