package com.abc;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

public class AccountTest {

    @Test
    public void normalDeposit() {
        Account account = new Account(Account.CHECKING);
        account.deposit(100);
        assertThat(account.getBalance(), equalTo(100.0));
    }

    @Test(expected = IllegalArgumentException.class)
    public void zeroDeposit() {
        Account account = new Account(Account.CHECKING);
        account.deposit(0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void negativeDeposit() {
        Account account = new Account(Account.CHECKING);
        account.deposit(-100);
    }

    @Test
    public void normalWithdrawal() {
        Account account = new Account(Account.CHECKING);
        account.withdraw(100);
        assertThat(account.getBalance(), equalTo(-100.0));
    }

    @Test(expected = IllegalArgumentException.class)
    public void zeroWithdrawal() {
        Account account = new Account(Account.CHECKING);
        account.withdraw(0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void negativeWithdrawal() {
        Account account = new Account(Account.CHECKING);
        account.withdraw(-100);
    }

}