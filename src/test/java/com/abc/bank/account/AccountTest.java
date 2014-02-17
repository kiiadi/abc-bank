package com.abc.bank.account;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.abc.bank.account.Account;
import com.abc.bank.exception.InsufficuentFundsException;

public class AccountTest {
    private static final double DOUBLE_DELTA = 1e-15;
    private Account savings;

    @Before
    public void setup(){
        //Doesn't matter which one we instantiate here as the abstract
        //behavior is being tested
        savings = new SavingsAccount();
    }

    @Test
    public void testDeposit() {
        double amount = 1000;
        savings.deposit(amount);
        Assert.assertEquals(amount, savings.getBalance(),DOUBLE_DELTA);
    }

    @Test
    public void testWithdraw() throws InsufficuentFundsException {
        double amount = 1000;
        savings.deposit(amount);
        double withdraw = 500;
        Assert.assertEquals(withdraw, savings.withdraw(withdraw), DOUBLE_DELTA);
    }

    @Test(expected = InsufficuentFundsException.class)
    public void testWithdrawThrowsIfInsufficientFunds() throws InsufficuentFundsException {
        double amount = 10;
        savings.deposit(amount);
        double withdraw = 500;
        Assert.assertEquals(withdraw, savings.withdraw(withdraw), DOUBLE_DELTA);
    }

    @Test
    public void testGetBalance() throws InsufficuentFundsException {
        double amount = 1000;
        savings.deposit(amount);
        double withdraw = 500;
        Assert.assertEquals(withdraw, savings.withdraw(withdraw), DOUBLE_DELTA);
        Assert.assertEquals(amount-withdraw, savings.getBalance(), DOUBLE_DELTA);
    }
}