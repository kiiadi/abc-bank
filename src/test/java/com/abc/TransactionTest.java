package com.abc;

import org.junit.Test;

import com.abc.BankConstants.TransactionType;

import static org.junit.Assert.assertTrue;

public class TransactionTest {
    @Test
    public void transaction() {
        Transaction t = new Transaction(TransactionType.DEPOSIT ,5);
        assertTrue(t instanceof Transaction);
    }
}
