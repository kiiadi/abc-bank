package com.abc;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

public class SavingsAccountTest extends AccountTest {

    @Override
    protected Account createAccount() {
        return new SavingsAccount();
    }

    @Test
    public void interestEarned() {
        account.deposit(1500.0);
        assertThat(account.interestEarned(), equalTo(2.0));
    }
}
