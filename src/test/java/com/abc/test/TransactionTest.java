package com.abc.test;

import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;

import org.junit.Test;

import com.abc.account.Transaction;
import com.abc.account.TransactionType;

/**
 * TODO: Delete this test. Not sure what's the point of this test.
 * @author ashok
 *
 */
public class TransactionTest {
    @Test
    public void transaction() {
        Transaction t = new Transaction(new BigDecimal("5"), TransactionType.Deposit);
        assertTrue(t instanceof Transaction);
    }
}
