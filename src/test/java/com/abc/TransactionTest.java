package com.abc;

import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Test;

import java.util.Calendar;
import java.util.Date;

import static org.junit.Assert.assertTrue;

public class TransactionTest {
    @Test
    public void shouldReturnAmountAndDatePassedIn() {
        Date time = Calendar.getInstance().getTime();
        Transaction transaction = new Transaction(5, time);

        Assert.assertThat(transaction.getAmount(), CoreMatchers.is(5.0));
        Assert.assertThat(transaction.getDate(), CoreMatchers.is(time));


    }
}
