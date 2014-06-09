package com.abc.accounts;

import org.junit.Assert;
import org.junit.Test;

public class MaxiSavingAccountTest {

    private static final double DOUBLE_DELTA = 1e-15;

    @Test
    public void shouldReturnCorrectInterestForMaxiSavingsAccountBelow1000 () throws Exception{
        Account savingAccount = new MaxiSavingAccount();
        savingAccount.deposit(1000);

        Assert.assertEquals(20, savingAccount.interestEarned(), DOUBLE_DELTA);
    }

    @Test
    public void shouldReturnCorrectInterestForMaxiSavingsAccountBetween1000And2000 () throws Exception{
        Account savingAccount = new MaxiSavingAccount();
        savingAccount.deposit(2000);

        Assert.assertEquals(70, savingAccount.interestEarned(), DOUBLE_DELTA);
    }

    @Test
    public void shouldReturnCorrectInterestForMaxiSavingsAccountAbove3000 () throws Exception{
        Account savingAccount = new MaxiSavingAccount();
        savingAccount.deposit(3000);

        Assert.assertEquals(170, savingAccount.interestEarned(), DOUBLE_DELTA);
    }
}
