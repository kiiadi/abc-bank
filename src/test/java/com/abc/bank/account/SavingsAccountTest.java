package com.abc.bank.account;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class SavingsAccountTest {

    private static final double DOUBLE_DELTA = 1e-15;
    private Account account;

    @Before
    public void setup(){
        account = AccountFactory.newAccount(AccountType.savings);
    }

    @Test
    public void testBelow1000() {
        account.deposit(500.0);
        assertEquals(0.5, account.interestEarned(), DOUBLE_DELTA);
    }
    
    @Test
    public void testAbove1000() {
        account.deposit(1500.0);
        assertEquals(2.0, account.interestEarned(), DOUBLE_DELTA);
    }
}
