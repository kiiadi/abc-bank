package com.abc;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

public class MaxiSavingsAccountTest extends AccountTest {

    @Override
    protected Account createAccount() {
        return new MaxiSavingsAccount();
    }

    @Test
    public void interestEarned() {
        account.deposit(3000.0);
        assertThat(account.interestEarned(), equalTo(170.0));
    }
}
