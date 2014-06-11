package com.abc;

import org.junit.Test;

import java.math.BigDecimal;
import java.util.SortedMap;
import java.util.TreeMap;

import static junit.framework.Assert.assertEquals;

/**
 * Tests for {@link com.abc.TieredRateProvider}
 */
public class TieredRateProviderTest {

    @Test
    public void testGetRates() throws Exception {

        SortedMap<BigDecimal, BigDecimal> rates = new TreeMap<>();
        BigDecimal rate1 = new BigDecimal("0.01");
        BigDecimal rate2 = new BigDecimal("0.05");
        BigDecimal rate3 = new BigDecimal("0.1");
        rates.put(new BigDecimal("500"), rate1);
        rates.put(new BigDecimal("1000"), rate2);
        rates.put(new BigDecimal("10000"), rate3);

        TieredRateProvider underTest = new TieredRateProvider(rates);

        assertEquals(rate1, underTest.getRate(BigDecimal.ZERO, null));
        assertEquals(rate1, underTest.getRate(BigDecimal.ONE, null));
        assertEquals(rate1, underTest.getRate(new BigDecimal("500"), null));

        assertEquals(rate2, underTest.getRate(new BigDecimal("500.01"), null));
        assertEquals(rate2, underTest.getRate(new BigDecimal("1000.00"), null));

        assertEquals(rate3, underTest.getRate(new BigDecimal("1000.01"), null));
    }

    @Test(expected = NoApplicableRateException.class)
    public void testGetRates_FailsWhenNoRateApplicable() throws Exception {

        SortedMap<BigDecimal, BigDecimal> rates = new TreeMap<>();
        rates.put(new BigDecimal("500"), new BigDecimal("0.01"));

        TieredRateProvider underTest = new TieredRateProvider(rates);
        underTest.getRate(new BigDecimal("500.01"), null);
    }
}
