package com.abc;

import org.junit.Test;

import java.math.BigDecimal;
import static junit.framework.Assert.assertEquals;

/**
 * Tests for {@link com.abc.WithdrawalAffectedRateProvider}
 */
public class WithdrawalAffectedRateProviderTest {
    @Test
    public void testGetRate() throws Exception {

        BigDecimal lowerRate = new BigDecimal("0.01");
        BigDecimal upperRate = new BigDecimal("0.02");
        WithdrawalAffectedRateProvider underTest = new WithdrawalAffectedRateProvider(lowerRate, upperRate, 10);

        BigDecimal someAmount = BigDecimal.ZERO;

        assertEquals("Lower rate expected", lowerRate, underTest.getRate(someAmount, 9));
        assertEquals("Upper rate expected", upperRate, underTest.getRate(someAmount, 10));
        assertEquals("Upper rate expected", upperRate, underTest.getRate(someAmount, 11));
    }
}
