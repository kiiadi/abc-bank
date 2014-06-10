package com.abc.accounts;


import com.abc.Transaction;
import com.abc.accounts.interestRateCalculator.InterestRateCalculator;
import com.abc.accounts.interestRateCalculator.StubInterestRateCalculator;
import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class SavingAccountTest {

    @Test
    public void shouldReturnCorrectInterestForSavingsAccountUnderOrEqualTo1000 () throws Exception{
        Account savingAccount = new SavingAccount(new StubInterestRateCalculator());
        savingAccount.deposit(100);
        Assert.assertThat(savingAccount.interestEarned(), CoreMatchers.is(0.1));
        savingAccount.deposit(900);
        Assert.assertThat(savingAccount.interestEarned(), CoreMatchers.is(1.0));
    }

    @Test
    public void shouldReturnCorrectInterestForSavingsAccountOver1000 () throws Exception{
        Account savingAccount = new SavingAccount(new InterestRateCalculator() {
            @Override
            public double calculateInterestRate(List<Transaction> transactions, double totalAmount) {
                if(totalAmount>1000)
                    return 0.01;
                else
                    return 0.001;
            }
        });
        savingAccount.deposit(2000);
        Assert.assertThat(savingAccount.interestEarned(), CoreMatchers.is(11.0));
    }




}
