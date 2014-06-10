package com.abc.accounts.interestRateCalculator;

import com.abc.Transaction;
import com.abc.accounts.interestRateCalculator.MaxiInterestCalculator;
import org.hamcrest.CoreMatchers;
import org.joda.time.DateTime;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class MaxiInterestCalculatorTest {

    @Test
    public void shouldReturnInterest5PercentWhenNoWithdrawlWithin10Days () throws Exception{
        MaxiInterestCalculator maxiInterestCalculator = new MaxiInterestCalculator();
        List<Transaction> transactions = Arrays.asList(
                                                    new Transaction(100, new Date()),
                                                    new Transaction(-100, new DateTime().minusDays(20).toDate()));
        double interestRate = maxiInterestCalculator.calculateInterestRate(transactions,0);

        Assert.assertThat(interestRate, CoreMatchers.is(0.5));


    }


    @Test
    public void shouldReturnInterestPointOnePercentWhenWithdrawWithin10Days () throws Exception{
        MaxiInterestCalculator maxiInterestCalculator = new MaxiInterestCalculator();
        List<Transaction> transactions = Arrays.asList(
                new Transaction(100, new Date()),
                new Transaction(-100, new DateTime().minusDays(9).toDate()));
        double interestRate = maxiInterestCalculator.calculateInterestRate(transactions,0);

        Assert.assertThat(interestRate, CoreMatchers.is(0.001));


    }

    @Test
    public void shouldReturnInterestPointOnePercentNoWithdraw () throws Exception{
        MaxiInterestCalculator maxiInterestCalculator = new MaxiInterestCalculator();
        List<Transaction> transactions = Arrays.asList(
                new Transaction(100, new Date()));

        double interestRate = maxiInterestCalculator.calculateInterestRate(transactions, 100);

        Assert.assertThat(interestRate, CoreMatchers.is(0.5));


    }

}
