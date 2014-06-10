package com.abc.accounts;

import com.abc.interestRateCalculators.InterestRateCalculator;
import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Test;

import static org.mockito.Mockito.*;

public class AbstractAccountTest {

    @Test
    public void shouldDepositCorrectAmountInAccount () throws Exception{
        InterestRateCalculator interestRateCalculator = mock(InterestRateCalculator.class);
        when(interestRateCalculator.calculateInterestRate(anyObject())).thenReturn(0.01);

        Account savingAccount = new SavingAccount(interestRateCalculator);

        savingAccount.deposit(100);

        Assert.assertThat(savingAccount.sumTransactions(), CoreMatchers.is(100.00));
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionWhenAmountNegativeOnDeposit () throws Exception{
        InterestRateCalculator interestRateCalculator = mock(InterestRateCalculator.class);
        when(interestRateCalculator.calculateInterestRate(anyObject())).thenReturn(0.01);

        Account savingAccount = new SavingAccount(interestRateCalculator);
        savingAccount.deposit(-1);

        Assert.fail();
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionWhenAmountZeroOnDeposit () throws Exception{

        InterestRateCalculator interestRateCalculator = mock(InterestRateCalculator.class);
        when(interestRateCalculator.calculateInterestRate(anyObject())).thenReturn(0.01);

        Account savingAccount = new SavingAccount(interestRateCalculator);

        savingAccount.deposit(0);

        Assert.fail();
    }

    @Test
    public void shouldWithdrawCorrectAmountInAccount () throws Exception{

        InterestRateCalculator interestRateCalculator = mock(InterestRateCalculator.class);
        when(interestRateCalculator.calculateInterestRate(anyObject())).thenReturn(0.01);

        Account savingAccount = new SavingAccount(interestRateCalculator);

        savingAccount.withdraw(100);

        Assert.assertThat(savingAccount.sumTransactions(), CoreMatchers.is(-100.00));
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionWhenAmountNegativeOnWithdraw () throws Exception{
        InterestRateCalculator interestRateCalculator = mock(InterestRateCalculator.class);
        when(interestRateCalculator.calculateInterestRate(anyObject())).thenReturn(0.01);

        Account savingAccount = new SavingAccount(interestRateCalculator);

        savingAccount.withdraw(-1);

        Assert.fail();
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionWhenAmountZeroOnWithdraw () throws Exception{

        InterestRateCalculator interestRateCalculator = mock(InterestRateCalculator.class);
        when(interestRateCalculator.calculateInterestRate(anyObject())).thenReturn(0.01);

        Account savingAccount = new SavingAccount(interestRateCalculator);

        savingAccount.withdraw(0);

        Assert.fail();
    }

    @Test
    public void shouldSumCorrectAmountInAccount () throws Exception{

        InterestRateCalculator interestRateCalculator = mock(InterestRateCalculator.class);
        when(interestRateCalculator.calculateInterestRate(anyObject())).thenReturn(0.01);

        Account savingAccount = new SavingAccount(interestRateCalculator);
        savingAccount.deposit(100);
        savingAccount.deposit(100);
        savingAccount.withdraw(20);

        Assert.assertThat(savingAccount.sumTransactions(), CoreMatchers.is(180.00));
    }


    @Test
    public void shouldTransferFromSavingsToCheckingAccount () throws Exception{
        InterestRateCalculator interestRateCalculator = mock(InterestRateCalculator.class);
        when(interestRateCalculator.calculateInterestRate(anyObject())).thenReturn(0.01);

        Account savingAccount = new SavingAccount(interestRateCalculator);
        savingAccount.deposit(100);

        Account checkingAccount = new CheckingAccount(interestRateCalculator);

        savingAccount.transferTo(checkingAccount, 10);

        Assert.assertThat(savingAccount.sumTransactions(), CoreMatchers.is(90.00));
        Assert.assertThat(checkingAccount.sumTransactions(), CoreMatchers.is(10.00));
    }

}
