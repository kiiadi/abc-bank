package com.abc;

import org.junit.Test;
import org.mockito.Mockito;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Date;
import static junit.framework.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

/**
 * Tests for {@link com.abc.InterestCalculator}
 */
public class InterestCalculatorTest {

    @Test
    public void testGetInterestAccrued_WhenNoTransactions() {
        AccountImpl account = Mockito.mock(AccountImpl.class);
        RateProvider rateProvider = Mockito.mock(RateProvider.class);

        InterestCalculator underTest = new InterestCalculator(account, rateProvider);

        Date date = DateUtils.getDate(2014, 1, 1);
        assertEquals(new BigDecimal("0.000000"), underTest.getInterestAccrued(date));
    }


    /**
     * The calculation yields a day of interest for constant balance (one transaction on account).
     * This is 1 day * 1/365 * 1% * 10.00 = 0.000274
     */
    @Test
    public void testGetInterestAccrued_ForOneDay_WhenBalanceIsConstant() {
        AccountImpl account = Mockito.mock(AccountImpl.class);
        Date transactionDate = DateUtils.getDate(2014, 1, 1);
        Transaction transaction = new Transaction(new BigDecimal("10.00"), transactionDate, new BigDecimal("10.00"));

        when(account.getTransactions()).thenReturn(Arrays.asList(transaction));
        when(account.getBalance(any(Date.class))).thenReturn(new BigDecimal("10.00"));
        when(account.getDaysSinceLastWithdrawal(any(Date.class))).thenReturn(null);

        RateProvider rateProvider = Mockito.mock(RateProvider.class);
        when(rateProvider.getRate(any(BigDecimal.class), any(Integer.class))).thenReturn(new BigDecimal("0.01"));

        InterestCalculator underTest = new InterestCalculator(account, rateProvider);

        Date observationDate = DateUtils.getDate(2014, 1, 2);
        assertEquals(new BigDecimal("0.000274"), underTest.getInterestAccrued(observationDate));
    }

    /**
     * The calculation yields a complete year of interest for constant balance.
     * This is 365 days * 1/365 * 1% * 10.00 = 0.10
     */
    @Test
    public void testGetInterestAccrued_ForOneYear_WhenBalanceIsConstant() {
        AccountImpl account = Mockito.mock(AccountImpl.class);
        Date transactionDate = DateUtils.getDate(2014, 1, 1);
        Transaction transaction = new Transaction(new BigDecimal("10.00"), transactionDate, new BigDecimal("10.00"));

        when(account.getTransactions()).thenReturn(Arrays.asList(transaction));
        when(account.getBalance(any(Date.class))).thenReturn(new BigDecimal("10.00"));
        when(account.getDaysSinceLastWithdrawal(any(Date.class))).thenReturn(null);

        RateProvider rateProvider = Mockito.mock(RateProvider.class);
        when(rateProvider.getRate(any(BigDecimal.class), any(Integer.class))).thenReturn(new BigDecimal("0.01"));

        InterestCalculator underTest = new InterestCalculator(account, rateProvider);

        Date observationDate = DateUtils.getDate(2015, 1, 1);
        assertEquals(new BigDecimal("0.100000"), underTest.getInterestAccrued(observationDate));
    }

    /**
     * The calculation yields a day of interest over a period when two transactions affect the balance.
     * This is (1 day * 1/365 * 1% * 10.00) + (1 day * 1/365 * 1% * 20.00)
     */
    @Test
    public void testGetInterestAccrued_ForTwoDays_WhenBalanceChanges() {
        AccountImpl account = Mockito.mock(AccountImpl.class);
        Date date1 = DateUtils.getDate(2014, 1, 1);
        Transaction transaction = new Transaction(new BigDecimal("10.00"), date1, new BigDecimal("10.00"));
        Date date2 = DateUtils.getDate(2014, 1, 2);
        Transaction transaction2 = new Transaction(new BigDecimal("10.00"), date2, new BigDecimal("20.00"));

        when(account.getTransactions()).thenReturn(Arrays.asList(transaction, transaction2));
        when(account.getBalance(date1)).thenReturn(new BigDecimal("10.00"));
        when(account.getBalance(date2)).thenReturn(new BigDecimal("20.00"));
        when(account.getDaysSinceLastWithdrawal(any(Date.class))).thenReturn(null);

        RateProvider rateProvider = Mockito.mock(RateProvider.class);
        when(rateProvider.getRate(any(BigDecimal.class), any(Integer.class))).thenReturn(new BigDecimal("0.01"));

        InterestCalculator underTest = new InterestCalculator(account, rateProvider);

        Date observationDate = DateUtils.getDate(2014, 1, 3);
        assertEquals(new BigDecimal("0.000822"), underTest.getInterestAccrued(observationDate));
    }

    /**
     * The calculation yields a day of interest for constant balance (one transaction on account) but a rate change on
     * second day.
     * This is (1 day * 1/365 * 1% * 10.00) + (1 day * 1/365 * 2% * 10.00)
     */
    @Test
    public void testGetInterestAccrued_ForTwoDays_WhenRateChanges() {
        AccountImpl account = Mockito.mock(AccountImpl.class);
        Date transactionDate = DateUtils.getDate(2014, 1, 1);
        BigDecimal amount = new BigDecimal("10.00");
        Transaction transaction = new Transaction(amount, transactionDate, amount);

        when(account.getTransactions()).thenReturn(Arrays.asList(transaction));
        when(account.getBalance(any(Date.class))).thenReturn(amount);
        when(account.getDaysSinceLastWithdrawal(any(Date.class))).thenReturn(null);

        RateProvider rateProvider = Mockito.mock(RateProvider.class);
        when(rateProvider.getRate(amount, null)).thenReturn(new BigDecimal("0.01")).thenReturn(new BigDecimal("0.02"));

        InterestCalculator underTest = new InterestCalculator(account, rateProvider);

        Date observationDate = DateUtils.getDate(2014, 1, 3);
        assertEquals(new BigDecimal("0.000822"), underTest.getInterestAccrued(observationDate));
    }
}
