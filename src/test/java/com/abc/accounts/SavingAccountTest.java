package com.abc.accounts;


import com.abc.accounts.interestRateCalculator.StubAmountInterestRateCalculator;
import com.abc.accounts.interestRateCalculator.StubInterestRateCalculator;
import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Test;

public class SavingAccountTest {

    @Test
    public void shouldReturnCorrectInterestForSavingsAccountUnderOrEqualTo1000 () throws Exception{
        Account savingAccount = new SavingAccount(new StubAmountInterestRateCalculator());
        savingAccount.deposit(100);
        Assert.assertThat(savingAccount.interestEarned(), CoreMatchers.is(0.1));
        savingAccount.deposit(900);
        Assert.assertThat(savingAccount.interestEarned(), CoreMatchers.is(1.0));
    }

    @Test
    public void shouldReturnCorrectInterestForSavingsAccountOver1000 () throws Exception{
        Account savingAccount = new SavingAccount(new StubAmountInterestRateCalculator());

        savingAccount.deposit(2000);
        Assert.assertThat(savingAccount.interestEarned(), CoreMatchers.is(11.0));
    }




}
