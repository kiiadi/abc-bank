package com.abc;

import org.junit.Test;

import static org.junit.Assert.assertTrue;


/*
 * 
 * Alex Lerner updates ( AlecLerner@gmail.com
 * 
 */


public class TransactionTest {
    @Test
    public void transaction() {
        Transaction t = new Transaction(5);
        assertTrue(t instanceof Transaction);
    }
}
