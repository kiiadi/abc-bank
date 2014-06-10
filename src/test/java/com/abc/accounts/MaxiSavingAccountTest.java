package com.abc.accounts;

import com.abc.interestRateCalculators.InterestRateCalculator;
import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Test;

import static org.mockito.Matchers.anyObject;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class MaxiSavingAccountTest {

    @Test
    public void shouldReturnCorrectInterestForMaxiSavingsWithWithdrawsWithin5Days () throws Exception{
        InterestRateCalculator interestRateCalculator = mock(InterestRateCalculator.class);
        when(interestRateCalculator.calculateInterestRate(anyObject())).thenReturn(0.001);

        Account maxiSavingAccount = new MaxiSavingAccount(interestRateCalculator);

        maxiSavingAccount.deposit(100);
        maxiSavingAccount.withdraw(10);

        Assert.assertThat(maxiSavingAccount.interestEarned(), CoreMatchers.is(0.09));

    }


}
