package com.abc.accounts;

import com.abc.accounts.interestRateCalculator.StubInterestRateCalculator;
import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Test;

public class CheckingAccountTest {

    @Test
    public void shouldReturnCorrectInterestForCheckingAccount () throws Exception{
        Account savingAccount = new CheckingAccount(new StubInterestRateCalculator());
        savingAccount.deposit(1000);

        Assert.assertThat(savingAccount.interestEarned(), CoreMatchers.is(1.00));

    }


}
