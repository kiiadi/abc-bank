/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abc;

import com.abc.account.MaxiSavingsAccount;
import com.abc.account.exception.InsufficientFundsException;
import com.abc.utils.DateUtils;
import java.math.BigDecimal;
import java.util.Calendar;

import org.junit.Test;
import org.junit.Ignore;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 *
 * @author hiecaxb
 */
public class MaxiSavingsAccountTest {

    /*
     ignored as new functionality added.
     comment the interestEarned overriden method in MaxiSavigsAccount to run this.
     */
    @Ignore
    public void interestEarned() {

        long _longInterest;
        BigDecimal interest = null;

        MaxiSavingsAccount maxiSavingsAccount = new MaxiSavingsAccount();

        // ----
        maxiSavingsAccount = new MaxiSavingsAccount();
        maxiSavingsAccount.deposit(new BigDecimal("500.00"));
        assertEquals(new BigDecimal("10.00"), maxiSavingsAccount.interestEarned());

        // ----
        maxiSavingsAccount = new MaxiSavingsAccount();
        maxiSavingsAccount.deposit(new BigDecimal("1000.00"));
        assertEquals(new BigDecimal("20.00"), maxiSavingsAccount.interestEarned());

        // ----
        maxiSavingsAccount = new MaxiSavingsAccount();
        maxiSavingsAccount.deposit(new BigDecimal("1500.00"));

        _longInterest = 20 + 25;
        interest = new BigDecimal(_longInterest);
        interest = interest.setScale(2);

        assertEquals(interest, maxiSavingsAccount.interestEarned());

        // ----
        maxiSavingsAccount = new MaxiSavingsAccount();
        maxiSavingsAccount.deposit(new BigDecimal("2000.00"));

        _longInterest = 20 + 50;
        interest = new BigDecimal(_longInterest);
        interest = interest.setScale(2);

        assertEquals(interest, maxiSavingsAccount.interestEarned());

        // ----
        maxiSavingsAccount = new MaxiSavingsAccount();
        maxiSavingsAccount.deposit(new BigDecimal("4000.00"));

        _longInterest = 20 + 50 + 200;
        interest = new BigDecimal(_longInterest);
        interest = interest.setScale(2);

        assertEquals(interest, maxiSavingsAccount.interestEarned());
    }

    @Test
    public void interestEarnedTenDayCheck() {
        MaxiSavingsAccount maxiSavingsAccount = new MaxiSavingsAccount();

        // ----
        maxiSavingsAccount = new MaxiSavingsAccount();
        maxiSavingsAccount.deposit(new BigDecimal("1000.00"));
        assertEquals(new BigDecimal("50.00"), maxiSavingsAccount.interestEarned());

        // ----
        maxiSavingsAccount = new MaxiSavingsAccount();
        maxiSavingsAccount.deposit(new BigDecimal("4000.00"));
        assertEquals(new BigDecimal("200.00"), maxiSavingsAccount.interestEarned());

        Calendar today = Calendar.getInstance();
        Calendar nineDaysAgo = Calendar.getInstance();
        Calendar tenDaysAgo = Calendar.getInstance();
        Calendar elevenDaysAgo = Calendar.getInstance();
        Calendar fifteenDaysAgo = Calendar.getInstance();

        nineDaysAgo.add(Calendar.DAY_OF_MONTH, -9);
        tenDaysAgo.add(Calendar.DAY_OF_MONTH, -10);
        elevenDaysAgo.add(Calendar.DAY_OF_MONTH, -11);

        // test withdrawal 9 days ago
        VirtualDateProvider testDateProvider = new VirtualDateProvider(nineDaysAgo);
        DateProvider dateProvider = new DateProvider();
        dateProvider.setInstance(testDateProvider);

        maxiSavingsAccount = new MaxiSavingsAccount();
        maxiSavingsAccount.deposit(new BigDecimal("1000.00"));

        try {
            maxiSavingsAccount.withdraw(new BigDecimal("100.00"));
        } catch (InsufficientFundsException isf) {
            fail();
        }

        testDateProvider = new VirtualDateProvider(today);
        dateProvider.setInstance(testDateProvider);

        assertEquals(new BigDecimal("0.90"), maxiSavingsAccount.interestEarned());

        // test withdrawal 10 days ago
        testDateProvider = new VirtualDateProvider(tenDaysAgo);
        dateProvider.setInstance(testDateProvider);

        maxiSavingsAccount = new MaxiSavingsAccount();
        maxiSavingsAccount.deposit(new BigDecimal("1000.00"));

        try {
            maxiSavingsAccount.withdraw(new BigDecimal("100.00"));
        } catch (InsufficientFundsException isf) {
            fail();
        }

        testDateProvider = new VirtualDateProvider(today);
        dateProvider.setInstance(testDateProvider);

        assertEquals(new BigDecimal("0.90"), maxiSavingsAccount.interestEarned());

    // test withdrawal 11 days ago
        testDateProvider = new VirtualDateProvider(elevenDaysAgo);
        dateProvider.setInstance(testDateProvider);

        maxiSavingsAccount = new MaxiSavingsAccount();
        maxiSavingsAccount.deposit(new BigDecimal("1000.00"));

        try {
            maxiSavingsAccount.withdraw(new BigDecimal("100.00"));
        } catch (InsufficientFundsException isf) {
            fail();
        }

        testDateProvider = new VirtualDateProvider(today);
        dateProvider.setInstance(testDateProvider);

        assertEquals(new BigDecimal("45.00"), maxiSavingsAccount.interestEarned());
    }

}
