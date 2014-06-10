package com.abc.accounts;

import com.abc.interestRateCalculators.InterestRateCalculator;
import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Test;

import static org.mockito.Matchers.anyObject;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class CheckingAccountTest {

    @Test
    public void shouldReturnCorrectInterestForCheckingAccount () throws Exception{
        InterestRateCalculator interestRateCalculator = mock(InterestRateCalculator.class);
        when(interestRateCalculator.calculateInterestRate(anyObject())).thenReturn(0.001);

        Account savingAccount = new CheckingAccount(interestRateCalculator);
        savingAccount.deposit(1000);

        Assert.assertThat(savingAccount.interestEarned(), CoreMatchers.is(1.00));

    }


}
