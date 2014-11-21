package com.abc;

import org.junit.Test;

import src.main.java.com.abc.Transaction;

public class TransactionTest {
    @Test
    public void transaction() {
        Transaction t = new Transaction(5);
        assertTrue(t instanceof Transaction);
    }
}
