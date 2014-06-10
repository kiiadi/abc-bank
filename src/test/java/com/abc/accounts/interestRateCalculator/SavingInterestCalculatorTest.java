package com.abc.accounts.interestRateCalculator;

import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Test;

public class SavingInterestCalculatorTest {

    @Test
    public void shouldReturnPointZeroOneForOneThousandAndLess () throws Exception{
        SavingInterestCalculator savingInterestCalculator = new SavingInterestCalculator();
        double interestRate = savingInterestCalculator.calculateInterestRate(null, 1000);

        Assert.assertThat(interestRate, CoreMatchers.is(0.001));

    }

    @Test
    public void shouldReturnPointZeroTwoForOverThousand () throws Exception{
        SavingInterestCalculator savingInterestCalculator = new SavingInterestCalculator();
        double interestRate = savingInterestCalculator.calculateInterestRate(null, 2000);

        Assert.assertThat(interestRate, CoreMatchers.is(0.002));

    }
}
