package com.abc.accounts;

import com.abc.interestRateCalculators.StubInterestRateCalculator;
import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Test;

public class MaxiSavingAccountTest {

    @Test
    public void shouldReturnCorrectInterestForMaxiSavingsWithWithdrawsWithin5Days () throws Exception{
        Account maxiSavingAccount = new MaxiSavingAccount(new StubInterestRateCalculator());

        maxiSavingAccount.deposit(100);
        maxiSavingAccount.withdraw(10);

        Assert.assertThat(maxiSavingAccount.interestEarned(), CoreMatchers.is(0.09));

    }


}
