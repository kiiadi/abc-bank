package com.abc.bank.account;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class CheckingAccountTest {

    private static final double DOUBLE_DELTA = 1e-15;
    private Account account;

    @Before
    public void setup(){
        account = AccountFactory.newAccount(AccountType.checking);
    }

    @Test
    public void test() {
        account.deposit(100.0);
        assertEquals(0.1, account.interestEarned(), DOUBLE_DELTA);
    }

}
