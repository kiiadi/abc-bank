package com.abc;


import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.*;

import static junit.framework.Assert.assertEquals;

public class UtilsTest {

    @Test
    public void testCorrectPluralOfWords() {
        assertEquals("1 ", Utils.correctPluralOfWord(1, ""));
        assertEquals("2 s", Utils.correctPluralOfWord(2, ""));
    }

    @Test
    public void testPositiveAmount() {
        Utils.ensurePositive(BigDecimal.ONE);
    }

    @Test(expected = NegativeOrZeroAmountException.class)
    public void testZeroAmount() {
        Utils.ensurePositive(BigDecimal.ZERO);
    }

    @Test(expected = NegativeOrZeroAmountException.class)
    public void testNegativeAmount() {
        Utils.ensurePositive(new BigDecimal(-1));
    }

    @Test
    public void testHasWithdrawalsForNdays() {
        Assert.assertFalse(Utils.hasWithdrawalsForNdays(Collections.<Transaction>emptyList(), 0));
        Assert.assertFalse(Utils.hasWithdrawalsForNdays(Collections.<Transaction>emptyList(), 1));

        List<Transaction> transactions = new ArrayList<>();
        transactions.add(new Transaction(BigDecimal.ONE.negate()));
        Assert.assertFalse(Utils.hasWithdrawalsForNdays(transactions, 0));
        Assert.assertTrue(Utils.hasWithdrawalsForNdays(transactions, 1));

        transactions = new ArrayList<>();
        transactions.add(new Transaction(BigDecimal.ONE.negate(), Utils.getDateNdaysAgo(10)));
        Assert.assertTrue(Utils.hasWithdrawalsForNdays(transactions, 10));

        transactions = new ArrayList<>();
        transactions.add(new Transaction(BigDecimal.ONE.negate(), Utils.getDateNdaysAgo(11)));
        Assert.assertFalse(Utils.hasWithdrawalsForNdays(transactions, 10));
    }


    @Test
    public void testGetDateNdaysAgo() {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, 2014);
        cal.set(Calendar.MONTH, 2);
        cal.set(Calendar.DAY_OF_MONTH, 23);
        cal.set(Calendar.HOUR_OF_DAY, 22);
        cal.set(Calendar.MINUTE, 30);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);

        Calendar c = Calendar.getInstance();
        c.setTime(Utils.getDateNdaysAgo(cal.getTime(), 0));
        assertTime(c, 2014, 2, 23, 0, 0, 0);

        c.setTime(Utils.getDateNdaysAgo(cal.getTime(), 1));
        assertTime(c, 2014, 2, 22, 0, 0, 0);

        c.setTime(Utils.getDateNdaysAgo(cal.getTime(), 28));
        assertTime(c, 2014, 1, 23, 0, 0, 0);
    }

    private void assertTime(Calendar c, int year, int month, int day, int hour, int min, int sec) {
        Assert.assertEquals("year", year, c.get(Calendar.YEAR));
        Assert.assertEquals("month", month, c.get(Calendar.MONTH));
        Assert.assertEquals("day", day, c.get(Calendar.DAY_OF_MONTH));
        Assert.assertEquals("hour", hour, c.get(Calendar.HOUR_OF_DAY));
        Assert.assertEquals("minute", min, c.get(Calendar.MINUTE));
        Assert.assertEquals("second", sec, c.get(Calendar.SECOND));
    }
}
