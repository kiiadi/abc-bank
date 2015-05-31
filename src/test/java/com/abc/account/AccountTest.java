package com.abc.account;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

public abstract class AccountTest {

    protected Account account;

    @Before
    public void init() {
        account = createAccount();

    }

    protected abstract Account createAccount();

    @Test
    public void normalDeposit() {
        account.deposit(100);
        assertThat(account.getBalance(), equalTo(100.0));
    }

    @Test(expected = IllegalArgumentException.class)
    public void zeroDeposit() {
        account.deposit(0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void negativeDeposit() {
        account.deposit(-100);
    }

    @Test
    public void normalWithdrawal() {
        account.withdraw(100);
        assertThat(account.getBalance(), equalTo(-100.0));
    }

    @Test(expected = IllegalArgumentException.class)
    public void zeroWithdrawal() {
        account.withdraw(0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void negativeWithdrawal() {
        account.withdraw(-100);
    }
}