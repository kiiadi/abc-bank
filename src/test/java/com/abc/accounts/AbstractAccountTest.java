package com.abc.accounts;

import com.abc.accounts.interestRateCalculator.StubInterestRateCalculator;
import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Test;

public class AbstractAccountTest {

    @Test
    public void shouldDepositCorrectAmountInAccount () throws Exception{
        Account savingAccount = new SavingAccount(new StubInterestRateCalculator());

        savingAccount.deposit(100);

        Assert.assertThat(savingAccount.sumTransactions(), CoreMatchers.is(100.00));
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionWhenAmountNegativeOnDeposit () throws Exception{
        Account savingAccount = new SavingAccount(new StubInterestRateCalculator());

        savingAccount.deposit(-1);

        Assert.fail();
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionWhenAmountZeroOnDeposit () throws Exception{
        Account savingAccount = new SavingAccount(new StubInterestRateCalculator());

        savingAccount.deposit(0);

        Assert.fail();
    }

    @Test
    public void shouldWithdrawCorrectAmountInAccount () throws Exception{
        Account savingAccount = new SavingAccount(new StubInterestRateCalculator());

        savingAccount.withdraw(100);

        Assert.assertThat(savingAccount.sumTransactions(), CoreMatchers.is(-100.00));
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionWhenAmountNegativeOnWithdraw () throws Exception{
        Account savingAccount = new SavingAccount(new StubInterestRateCalculator());

        savingAccount.withdraw(-1);

        Assert.fail();
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionWhenAmountZeroOnWithdraw () throws Exception{
        Account savingAccount = new SavingAccount(new StubInterestRateCalculator());

        savingAccount.withdraw(0);

        Assert.fail();
    }

    @Test
    public void shouldSumCorrectAmountInAccount () throws Exception{
        Account savingAccount = new SavingAccount(new StubInterestRateCalculator());
        savingAccount.deposit(100);
        savingAccount.deposit(100);
        savingAccount.withdraw(20);

        Assert.assertThat(savingAccount.sumTransactions(), CoreMatchers.is(180.00));
    }


    @Test
    public void shouldTransferFromSavingsToCheckingAccount () throws Exception{
        Account savingAccount = new SavingAccount(new StubInterestRateCalculator());
        savingAccount.deposit(100);

        Account checkingAccount = new CheckingAccount(new StubInterestRateCalculator());

        savingAccount.transferTo(checkingAccount, 10);

        Assert.assertThat(savingAccount.sumTransactions(), CoreMatchers.is(90.00));
        Assert.assertThat(checkingAccount.sumTransactions(), CoreMatchers.is(10.00));
    }

}
