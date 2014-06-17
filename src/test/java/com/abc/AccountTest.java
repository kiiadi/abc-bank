package com.abc;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

/**
 * User: vachaspathy
 * Date: 6/16/14
 */
public class AccountTest {
    private static final double DOUBLE_DELTA = 1e-10;

    private Account checking, saving, maxiSavings;


    @Before
    public void setUp(){
         checking = new Account(Account.CHECKING);
         saving = new Account(Account.SAVINGS);
        maxiSavings = new Account(Account.MAXI_SAVINGS);
    }

    @Test
    public void checkingAccountSuccess(){
        assertEquals(Account.CHECKING,checking.getAccountType());
    }

    @Test(expected = IllegalArgumentException.class)
    public void notValidAccountType(){
        Account a = new Account(4);
    }

    @Test
    public void checkingAccountFailueWithMaxiSavings(){
        assertNotEquals(Account.MAXI_SAVINGS,checking.getAccountType());
    }

    @Test
    public void noTransactionsInChecking(){
        assertEquals(0, checking.getTransactions().size());
    }

    @Test(expected = IllegalArgumentException.class)
    public void depositLessThanZero(){
        checking.deposit(-200);
    }

    @Test(expected = IllegalArgumentException.class)
    public void depositEqualsZero(){
        checking.deposit(0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void withdrawLessThanZero(){
        checking.withdraw(-200);
    }

    @Test(expected = IllegalArgumentException.class)
    public void withdrawEqualsZero(){
        checking.deposit(0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void balanceLessThanDeposit(){
        checking.withdraw(200);
    }

    @Test
    public void withdrawalWithBalance(){
        checking.deposit(1000.20);
        checking.withdraw(500.19);
        assertEquals(500.01, checking.getAccountBalance(),DOUBLE_DELTA);
    }

    @Test
    public void checkAccountBalanceCalculation(){
        checking.deposit(1000.35);
        checking.withdraw(540.21);
        assertEquals(460.14, checking.getAccountBalance(),DOUBLE_DELTA);
    }

    @Test
    public void interestCalculationChecking(){
        checking.deposit(1000);
        assertEquals(1, checking.interestEarned(), DOUBLE_DELTA);
    }

    @Test
    public void interestCalculationSavingBelow1000(){
        saving.deposit(500);
        assertEquals(0.50, saving.interestEarned(), DOUBLE_DELTA);
    }

    @Test
    public void interestCalculationSavingAt1000(){
        saving.deposit(1000);
        assertEquals(1, saving.interestEarned(), DOUBLE_DELTA);
    }

    @Test
    public void interestCalculationSavingAbove1000(){
        saving.deposit(1500);
        assertEquals(2, saving.interestEarned(), DOUBLE_DELTA);
    }

    //Old Test Case that reflects different formula for Maxi Saving Account interest calculation
    @Ignore
    public void interestCalculationMaxiSavingAt1000(){
        maxiSavings.deposit(1000);
        assertEquals(20, maxiSavings.interestEarned(), DOUBLE_DELTA);
    }

    //Old Test Case that reflects different formula for Maxi Saving Account interest calculation
    @Ignore
    public void interestCalculationMaxiSavingAt2000(){
        maxiSavings.deposit(2000);
        assertEquals(70, maxiSavings.interestEarned(), DOUBLE_DELTA);
    }

    //Old Test Case that reflects different formula for Maxi Saving Account interest calculation
    @Ignore
    public void interestCalculationMaxiSavingAbove2000(){
        maxiSavings.deposit(3000);
        assertEquals(170, maxiSavings.interestEarned(), DOUBLE_DELTA);
    }

    @Test
    public void interestMaxiSavingsNoWithdrawl(){
        maxiSavings.deposit(1000);
        maxiSavings.deposit(1000);
        maxiSavings.deposit(1000);
        maxiSavings.deposit(1000);
        maxiSavings.deposit(1000);
        maxiSavings.deposit(1000);
        maxiSavings.deposit(1000);
        maxiSavings.deposit(1000);
        maxiSavings.deposit(1000);
        maxiSavings.deposit(1000);
        maxiSavings.deposit(1000);
        maxiSavings.deposit(1000);
        assertEquals(600, maxiSavings.interestEarned(), DOUBLE_DELTA);
    }

    @Test
    public void interestMaxiSavingsNoWithdrawlIn10Transactions(){
        maxiSavings.deposit(1000);
        maxiSavings.withdraw(200);

        maxiSavings.deposit(1000);
        maxiSavings.deposit(1000);
        maxiSavings.deposit(1000);
        maxiSavings.deposit(1000);
        maxiSavings.deposit(1000);
        maxiSavings.deposit(1000);
        maxiSavings.deposit(1000);
        maxiSavings.deposit(1000);
        maxiSavings.deposit(1000);
        maxiSavings.deposit(1000);
        assertEquals(540, maxiSavings.interestEarned(), DOUBLE_DELTA);
    }

    @Test
    public void interestMaxiSavingsWithdrawlIn10Transactions(){
        maxiSavings.deposit(1000);
        maxiSavings.deposit(1000);
        maxiSavings.deposit(1000);
        maxiSavings.deposit(1000);
        maxiSavings.deposit(1000);
        maxiSavings.withdraw(1000);

        maxiSavings.deposit(1000);
        maxiSavings.deposit(1000);
        maxiSavings.deposit(1000);
        maxiSavings.deposit(1000);
        maxiSavings.deposit(1000);
        maxiSavings.deposit(1000);
        assertEquals(10, maxiSavings.interestEarned(), DOUBLE_DELTA);
    }

}
