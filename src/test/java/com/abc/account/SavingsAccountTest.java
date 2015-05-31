package com.abc.account;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class SavingsAccountTest extends AccountTest {

    private static final double DOUBLE_DELTA = 1.0e-15;

    @Override
    protected Account createAccount() {
        return new SavingsAccount();
    }

    @Test
    public void interestEarned() {
        account.deposit(1500.0);
        assertEquals(2.0, account.interestEarned(), DOUBLE_DELTA);
    }
}
