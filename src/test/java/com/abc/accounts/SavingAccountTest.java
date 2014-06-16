package com.abc.accounts;


import com.abc.interestRateCalculators.InterestRateCalculator;
import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Test;

import static org.mockito.Matchers.anyObject;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class SavingAccountTest {

    @Test
    public void shouldReturnCorrectInterestForSavingsAccountUnderOrEqualTo1000 () throws Exception{
        InterestRateCalculator interestRateCalculator = mock(InterestRateCalculator.class);
        when(interestRateCalculator.calculateInterestRate(anyObject())).thenReturn(0.001);

        Account savingAccount = new SavingAccount(interestRateCalculator);
        savingAccount.deposit(100);
        Assert.assertThat(savingAccount.interestEarned(), CoreMatchers.is(0.1));
        savingAccount.deposit(900);
        Assert.assertThat(savingAccount.interestEarned(), CoreMatchers.is(1.0));
    }

    @Test
    public void shouldReturnCorrectInterestForSavingsAccountOver1000 () throws Exception{

        InterestRateCalculator<Double> interestRateCalculator = mock(InterestRateCalculator.class);
        when(interestRateCalculator.calculateInterestRate(1000.00)).thenReturn(0.001);
        when(interestRateCalculator.calculateInterestRate(2000.00)).thenReturn(0.01);

        Account savingAccount = new SavingAccount(interestRateCalculator);

        savingAccount.deposit(2000);
        Assert.assertThat(savingAccount.interestEarned(), CoreMatchers.is(11.0));
    }




}
