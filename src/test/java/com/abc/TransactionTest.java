package com.abc;

import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.assertTrue;

public class TransactionTest {
    @Test
    public void debitTransaction() {
        Transaction t = new Transaction(new BigDecimal(5),TransactionType.DEBIT);
        assertTrue(t instanceof Transaction);
    }

    @Test
    public void creditTransaction() {
        Transaction t = new Transaction(new BigDecimal(5),TransactionType.CREDIT);
        assertTrue(t instanceof Transaction);
    }
}
