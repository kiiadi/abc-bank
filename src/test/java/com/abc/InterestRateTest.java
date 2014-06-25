package com.abc;

import org.junit.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;

import static org.junit.Assert.assertEquals;


public class InterestRateTest {

    @Test
    public void testCheckingAccount() {
        AccountType checkingAccount = AccountType.CHECKING;
        assertEquals(new BigDecimal("1.70").setScale(2,RoundingMode.HALF_EVEN), checkingAccount.interestEarned(new BigDecimal(1700)));

    }

    @Test
    public void testSavingsAccountBalanceLessThan1000() {
        AccountType savingsAccount = AccountType.SAVINGS;
        assertEquals(new BigDecimal("0.70"), savingsAccount.interestEarned(new BigDecimal(700)));
    }

    @Test
    public void testSavingsAccountBalanceGreaterThan1000() {
        AccountType savingsAccount = AccountType.SAVINGS;
        assertEquals(new BigDecimal("1.40"), savingsAccount.interestEarned(new BigDecimal(1200)));
    }

    @Test
    public void testMaxiSavingsAccountBalanceLessThan1000() {
        AccountType savingsAccount = AccountType.MAXI_SAVINGS;
        assertEquals(new BigDecimal("4.00"), savingsAccount.interestEarned(new BigDecimal(200)));
    }

    @Test
    public void testMaxiSavingsAccountBalanceGreaterThan1000LessThan2000() {
        AccountType savingsAccount = AccountType.MAXI_SAVINGS;
        assertEquals(new BigDecimal("30.00"), savingsAccount.interestEarned(new BigDecimal(1200)));
    }

    @Test
    public void testMaxiAccountBalanceGreaterThan2000() {
        AccountType savingsAccount = AccountType.MAXI_SAVINGS;
        assertEquals(new BigDecimal("90.00"), savingsAccount.interestEarned(new BigDecimal(2200)));
    }



}
