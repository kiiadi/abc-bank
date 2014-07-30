package com.abc;

import org.joda.time.DateTime;
import org.joda.time.DateTimeUtils;
import org.joda.time.DateTimeZone;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class TransactionTest {
    @Test
    public void shouldKnowWhenTheTransactionTookPlace() {
        final DateTime dateTime = new DateTime(2001, 3, 1, 12, 34, 23, 0, DateTimeZone.UTC);
        DateTimeUtils.setCurrentMillisFixed(dateTime.getMillis());
        try {
            final Transaction transaction = new Transaction(5);
            assertThat(new DateTime(transaction.getTransactionDate().getTime(), DateTimeZone.UTC), is(dateTime));
        } finally {
            DateTimeUtils.setCurrentMillisSystem();
        }
    }
}
