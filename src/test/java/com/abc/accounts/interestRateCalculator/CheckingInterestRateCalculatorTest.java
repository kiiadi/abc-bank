package com.abc.accounts.interestRateCalculator;

import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Test;

public class CheckingInterestRateCalculatorTest {

    @Test
    public void shouldReturnFlatRateInterestRate () throws Exception{
        CheckingInterestRateCalculator checkingInterestRateCalculator = new CheckingInterestRateCalculator();
        double interestRate = checkingInterestRateCalculator.calculateInterestRate(null, 0);

        Assert.assertThat(interestRate, CoreMatchers.is(0.001));

    }
}
