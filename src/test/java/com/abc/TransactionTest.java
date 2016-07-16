package com.abc;

import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;

public class TransactionTest {
    private static final double DOUBLE_DELTA = 1e-15;

    @Test
    public void transaction() {
        Transaction transaction = new Transaction(new BigDecimal(5), false);
        assertEquals(5.0, transaction.amount.doubleValue(), DOUBLE_DELTA);
    }

}
