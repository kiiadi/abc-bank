package com.abc;

import org.junit.Before;
import org.junit.Test;

import java.util.Calendar;
import java.util.Date;

import static org.junit.Assert.*;

public class TransactionTest {
    private final int TIME_DELTA = 1; // 1 ms time delay
    private final double DOUBLE_DELTA = 1e-15;

    private Transaction positiveAmount;
    private Date transactionDate;

    @Before
    public void setUp(){
        positiveAmount = new Transaction(500);
        transactionDate = Calendar.getInstance().getTime();
    }

    @Test
    public void transactionCompareFail(){
        Transaction anotherPositive = new Transaction(500);
        assertNotSame(positiveAmount, anotherPositive);
    }

    @Test
    public void getDateSuccess() {
        assertEquals(transactionDate.getTime(), positiveAmount.getTransactionDate().getTime(), TIME_DELTA);
    }

    @Test
    public void getDateFailedUsingCurrentTime() {
        assertEquals(Calendar.getInstance().getTime().getTime(),positiveAmount.getTransactionDate().getTime());
    }

    @Test
    public void getAccountSuccess() {
        assertEquals(500, positiveAmount.getAmount(), DOUBLE_DELTA);
    }

    @Test
    public void getAccountFailedNegative() {
        assertNotEquals(-500, positiveAmount.getAmount(), DOUBLE_DELTA);
    }

    @Test
    public void negativeTransction(){
        Transaction negative = new Transaction(-500);
        assertEquals(-500, negative.getAmount(),DOUBLE_DELTA);
    }
}
