package com.abc;

import org.junit.Test;

import java.math.BigDecimal;
import java.util.Date;
import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Tests for {@link com.abc.Transaction}
 */
public class TransactionTest {
    private static BigDecimal AMOUNT = new BigDecimal("100.00");
    private static Date DATE = DateUtils.getDate(2014, 1, 1);
    private static BigDecimal BALANCE = new BigDecimal("200.00");

    @Test
    public void testValues() throws Exception {
        Transaction underTest = new Transaction(AMOUNT, DATE, BALANCE);

        assertEquals(AMOUNT, underTest.getAmount());
        assertEquals(DATE, underTest.getTransactionDate());
        assertEquals(BALANCE, underTest.getResultingBalance());
    }

    @Test
    public void testIsWithdrawal_WhenTrue() throws Exception {
        Transaction underTest = new Transaction(AMOUNT.negate(), DATE, BALANCE);
        assertTrue(underTest.isWithdrawal());
    }

    @Test
    public void testIsWithdrawal_WhenFalse() throws Exception {
        Transaction underTest = new Transaction(AMOUNT, DATE, BALANCE);
        assertFalse(underTest.isWithdrawal());
    }
}
