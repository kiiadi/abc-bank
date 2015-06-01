package com.abc.account;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CheckingAccountTest extends AccountTest {

    private static final double DOUBLE_DELTA = 1.0e-15;

    @Override
    protected Account createAccount() {
        return new CheckingAccount();
    }

    @Test
    public void interestEarned() {
        account.deposit(100.0);
        assertEquals(0.1, account.interestEarned(), DOUBLE_DELTA);
    }
}
