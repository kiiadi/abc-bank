package com.abc.account;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

public class CheckingAccountTest extends AccountTest {

    @Override
    protected Account createAccount() {
        return new CheckingAccount();
    }

    @Test
    public void interestEarned() {
        account.deposit(100.0);
        assertThat(account.interestEarned(), equalTo(0.1));
    }
}
