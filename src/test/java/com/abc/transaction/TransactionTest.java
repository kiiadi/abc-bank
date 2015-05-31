package com.abc.transaction;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

public class TransactionTest {
    @Test
    public void deposit() {
        Transaction transaction = new DepositTransaction(5);
        assertThat(transaction.getAmount(), equalTo(5.0));
        assertThat(transaction.applyTo(2), equalTo(7.0));
    }

    @Test
    public void withdrawal() {
        Transaction transaction = new WithdrawalTransaction(5);
        assertThat(transaction.getAmount(), equalTo(5.0));
        assertThat(transaction.applyTo(8), equalTo(3.0));
    }

}
