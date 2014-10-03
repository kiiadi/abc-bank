package com.abc;

import org.junit.Test;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;

public class TransactionTest {
	
    private static final double DOUBLE_DELTA = 1e-12;
    
    @Test
    public void depositTransaction() {
        Transaction t = new Deposit(5);
        assertTrue(t instanceof Transaction);
    }
    @Test
    public void withdrawTransaction() {
        Transaction t = new Withdraw(5);
        assertEquals(t.amount, -5.0, DOUBLE_DELTA);
        assertTrue(t instanceof Transaction);
    }
    @Test
    public void transferTransaction() {
        Transaction t = new Transfer(5);
        assertTrue(t instanceof Transaction);
    }
}
