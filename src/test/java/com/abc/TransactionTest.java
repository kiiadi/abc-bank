package com.abc;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;
import java.math.RoundingMode;

import org.junit.Test;

public class TransactionTest {
    @Test
    public void transactionAmount() {
        Transaction t = new Transaction(new BigDecimal("5"));        
        assertEquals(new BigDecimal("5").setScale(2, RoundingMode.HALF_EVEN), 
        		Money.dollars(t.getAmount()).getAmount());
    }
 
}
