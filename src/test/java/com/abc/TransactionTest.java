package com.abc;

import java.text.ParseException;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class TransactionTest {
	
	private static final String DATE = "Fri Mar 14 14:45:17 EDT 2014";
	
    @Test
    public void transactionDefault() {
        Transaction t = new Transaction(5);
        assertTrue(t instanceof Transaction);
    }
    @Test
    public void transactionWithDate() throws ParseException {
    	
        Transaction t = new Transaction(5, DateProvider.getInstance().getDate(DATE));
        assertTrue(t instanceof Transaction);
        assertEquals(t.getTransactionDate().toString(),"Fri Mar 14 14:45:17 EDT 2014");
    }
}
