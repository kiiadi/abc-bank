package com.abc.bank.account;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.abc.bank.exception.InsufficuentFundsException;

public class MaxiSavingsAccountTest {

    private static final double DOUBLE_DELTA = 1e-15;
    private Account account;

    @Before
    public void setup(){
        account = AccountFactory.newAccount(AccountType.maxiSavings);
    }

    @Test
    public void testInterestRateWithNoWithdrawasInPast10Days() {
        account.deposit(3000.0);
        assertEquals(150.0, account.interestEarned(), DOUBLE_DELTA);
    }
    
    @Test
    public void testInterestRateIfWithdrawalInPast10Days() throws InsufficuentFundsException {
        account.deposit(6000.0);
        account.withdraw(1000.0);
        assertEquals(5, account.interestEarned(), DOUBLE_DELTA);
    }
}
