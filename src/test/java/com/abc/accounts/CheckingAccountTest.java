package com.abc.accounts;

import com.abc.Account;
import org.junit.Assert;
import org.junit.Test;

public class CheckingAccountTest {
    private static final double DOUBLE_DELTA = 1e-15;

    @Test
    public void shouldReturnCorrectInterestForCheckingAccount () throws Exception{
        Account savingAccount = new CheckingAccount();
        savingAccount.deposit(1000);

        Assert.assertEquals(1.0, savingAccount.interestEarned(), DOUBLE_DELTA);
    }


}
