package com.abc;

import org.junit.Test;

import static org.junit.Assert.*;

public class TransactionTest {
    @Test
    public void transaction() {
        Transaction t = new Transaction(5);
        assertTrue(t instanceof Transaction);
    }
    
    @Test
    public void testTransactionDate(){
    	Transaction t=new Transaction(500);
    	assertEquals(0,t.getTransactionDate().getTime()-System.currentTimeMillis(),10);
    }
}
