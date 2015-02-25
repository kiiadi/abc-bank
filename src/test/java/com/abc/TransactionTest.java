package com.abc;

import java.math.BigDecimal;
import java.util.Calendar;

import org.junit.Test;

import com.abc.BankConstants.TransactionType;

import static org.junit.Assert.assertTrue;

public class TransactionTest {
    @Test
    public void transaction() {
        Transaction t = new Transaction(5,new BigDecimal("5"), 1, 1, Calendar.getInstance().getTime(), TransactionType.DEPOSIT);
        assertTrue(t instanceof Transaction);
    }
}
