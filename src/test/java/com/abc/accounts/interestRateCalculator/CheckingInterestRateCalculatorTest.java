package com.abc.accounts.interestRateCalculator;

import com.abc.Transaction;
import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;

public class CheckingInterestRateCalculatorTest {

    @Test
    public void shouldReturnFlatRateInterestRate () throws Exception{
        CheckingInterestRateCalculator checkingInterestRateCalculator = new CheckingInterestRateCalculator();
        double interestRate = checkingInterestRateCalculator.calculateInterestRate(new ArrayList<Transaction>());

        Assert.assertThat(interestRate, CoreMatchers.is(0.001));

    }
}
