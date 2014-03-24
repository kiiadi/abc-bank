package com.abc;

import junit.framework.Assert;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Date;

public class TransactionTest {
    @Test
    public void testDepositTransaction() {
        BigDecimal five = new BigDecimal(5);
        Transaction t = new Transaction(new BigDecimal(5));
        Assert.assertEquals(five, t.getAmount());
        Assert.assertTrue(t.isDeposit());
        Assert.assertNotNull(t.getDate());
    }

    @Test
    public void testWithdrawalTransaction() {
        BigDecimal five = new BigDecimal(5).negate();
        Transaction t = new Transaction(new BigDecimal(5).negate());
        Assert.assertEquals(five, t.getAmount());
        Assert.assertTrue(t.isWithdrawal());
        Assert.assertNotNull(t.getDate());
    }

    @Test
    public void testDateIsImmutable() {
        Transaction t = new Transaction(new BigDecimal(0));
        Date date = t.getDate();
        Assert.assertNotNull(date);
        long time = date.getTime();
        long newTime = 123L;
        Assert.assertFalse(time == newTime);
        date.setTime(newTime);
        Date dateAfter = t.getDate();
        Assert.assertEquals(time, dateAfter.getTime());

    }
}
