package com.abc;

import org.junit.Test;

import static org.junit.Assert.assertTrue;

import java.util.Date;

public class TransactionTest {
    @Test
    public void transaction() {
        Transaction t = new Transaction(new Date(), TransactionType.DEPOSIT, 100);
      //  assertTrue(t instanceof Transaction);
    }
}
