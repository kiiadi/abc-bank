package com.abc;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class TransactionTest {
    
    @Test
    public void transaction() {
        Transaction t = new Transaction(5);
        assertTrue(t instanceof Transaction);
    }

    @Test
    public void testDeposit() {
        double amount = 100;
        Transaction t = new Transaction(amount);
        assertTrue(t.isDeposit());
    }
    
    @Test
    public void testWithdraw() {
        double amount = -100;
        Transaction t = new Transaction(amount);
        assertTrue(!t.isDeposit());
    }
    
    @Test
    public void testWithdrawAge() {
        double amount = -100;
        Transaction t = new Transaction(amount);
        assertEquals(1, t.getWithdrawAge());
    }
    
    @Test
    public void testDepositAge() {
        double amount = 100;
        Transaction t = new Transaction(amount);
        assertEquals(1, t.getDepositAge());
    }
    
    
}
