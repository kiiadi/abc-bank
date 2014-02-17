package com.abc;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by serge on 2/13/14.
 */
public class TransactionTest {
    private Transaction t;
    private static final double DOUBLE_DELTA = 1e-15;

    @Before
    public void setUp() throws Exception {
        t = new Transaction(5);
    }

    @Test
    public void transaction(){
    assertTrue(t instanceof Transaction);
    }

    @Test
    public void transactionAmount(){
        assertEquals(5.0, t.amount, DOUBLE_DELTA);
    }
}
