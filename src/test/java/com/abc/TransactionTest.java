package com.abc;

import org.junit.Test;

import static org.junit.Assert.assertTrue;

import java.util.Date;

public class TransactionTest {
    @Test
    public void transaction() {
        Transaction t = new Transaction(5);
        assertTrue(t instanceof Transaction);
    }
    
    @Test
    public void getTransactionDate(){
    	Transaction t = new Transaction(5);
    	Date transactionDate = t.getTransactionDate();
    	assertTrue(transactionDate instanceof Date);
    }
    
}
