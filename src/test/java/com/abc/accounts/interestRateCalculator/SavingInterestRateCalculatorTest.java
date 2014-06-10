package com.abc.accounts.interestRateCalculator;

import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Test;

public class SavingInterestRateCalculatorTest {

    @Test
    public void shouldReturnPointZeroOneForOneThousandAndLess () throws Exception{
        SavingInterestRateCalculator savingInterestRateCalculator = new SavingInterestRateCalculator();
        double interestRate = savingInterestRateCalculator.calculateInterestRate(null, 1000);

        Assert.assertThat(interestRate, CoreMatchers.is(0.001));

    }

    @Test
    public void shouldReturnPointZeroTwoForOverThousand () throws Exception{
        SavingInterestRateCalculator savingInterestRateCalculator = new SavingInterestRateCalculator();
        double interestRate = savingInterestRateCalculator.calculateInterestRate(null, 2000);

        Assert.assertThat(interestRate, CoreMatchers.is(0.002));

    }
}
