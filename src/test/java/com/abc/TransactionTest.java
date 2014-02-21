package com.abc;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.abc.Transaction;

/**
 * @author Prachi
 */
public class TransactionTest {
	
    @Test
    public void transaction() {
        Transaction t = new Transaction(5, null);
        assertTrue(t instanceof Transaction);
    }
}
