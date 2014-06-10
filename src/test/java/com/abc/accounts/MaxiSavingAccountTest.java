package com.abc.accounts;

import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Test;

public class MaxiSavingAccountTest {

    @Test
    public void shouldReturnCorrectInterestForMaxiSavingsAccountBelowOrEqualTo1000 () throws Exception{
        Account maxiSavingAccount = new MaxiSavingAccount();

        maxiSavingAccount.deposit(100);
        Assert.assertThat(maxiSavingAccount.interestEarned(), CoreMatchers.is(2.00));

        maxiSavingAccount.deposit(900);
        Assert.assertThat(maxiSavingAccount.interestEarned(), CoreMatchers.is(20.00));

    }

    @Test
    public void shouldReturnCorrectInterestForMaxiSavingsAccountBetween1000And2000 () throws Exception{
        Account maxiSavingAccount = new MaxiSavingAccount();
        maxiSavingAccount.deposit(2000);
        Assert.assertThat(maxiSavingAccount.interestEarned(), CoreMatchers.is(70.00));

    }

    @Test
    public void shouldReturnCorrectInterestForMaxiSavingsAccountAbove3000 () throws Exception{
        Account maxiSavingAccount = new MaxiSavingAccount();
        maxiSavingAccount.deposit(3000);
        Assert.assertThat(maxiSavingAccount.interestEarned(), CoreMatchers.is(170.00));

    }
}
