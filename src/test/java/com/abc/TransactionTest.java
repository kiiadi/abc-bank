package com.abc;

import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;

/**
 * The Class TransactionTest.
 */
public class TransactionTest {
	
    /** The Constant DOUBLE_DELTA. */
    private static final double DOUBLE_DELTA = 1e-15;
    
    /** The static DateProvider instance */
    private static DateProvider dateProvider = null;
    
    
    static {
    	dateProvider = DateProvider.getInstance();
    }

    /**
     * Test Deposit Transaction Creation.
     */
    @Test
    public void testDepositTransactionCreation() {
        Transaction t = new Transaction(9.50, Transaction.DEPOSIT);
        assertTrue(t instanceof Transaction);
    }
     
    
    /**
     * Test Deposit Transaction Amount.
     */
    @Test
    public void testDepositTransactionDate() {
        Transaction t = new Transaction(5, Transaction.DEPOSIT);        
        Date now = DateProvider.getInstance().now();
        
        assertEquals(t.getTransactionDate(), now);
    }
    
    
    /**
     * Test Deposit Transaction Amount 
     */
    @Test
    public void testDepositTransactionAmount() {
        Transaction t = new Transaction(5, Transaction.DEPOSIT);
       
        assertEquals(5.0, t.getTransactionAmount(), DOUBLE_DELTA);
    }
    /*--------------------------------------------------------------*/
    
    
    /**
     * Test Withdrawal Transaction Creation.
     */
    @Test
    public void testWithdrawalTransactionCreation() {
        Transaction t = new Transaction(5, Transaction.WITHDRAWAL);
        assertTrue(t instanceof Transaction);
    }
     
    
    /**
     * Test Withdrawal Transaction Amount.
     */
    @Test
    @SuppressWarnings("static-access")
    public void testWithdrawalTransactionDate() {
        Transaction t = new Transaction(5, Transaction.WITHDRAWAL);        
        
        DateProvider dp = DateProvider.getInstance();
        Date now = dp.now();                     
        
        assertEquals(dp.makeDateTimeStr(t.getTransactionDate()), dp.makeDateTimeStr(now));
    }
    
    
    /**
     * Test Withdrawal Transaction Amount 
     */
    @Test
    public void testWithdrawalTransactionAmount() {
        Transaction t = new Transaction(5, Transaction.WITHDRAWAL);
                
        assertEquals(5.0, t.getTransactionAmount(), DOUBLE_DELTA);
    }
    /*--------------------------------------------------------------*/
    
    
    /**
     * Test Zero Transaction Creation.
     */
    @Test
    public void testZeroTransactionCreation() {
        Transaction t = new Transaction(0.0, Transaction.DEPOSIT);
        assertTrue(t instanceof Transaction);
    }
     
    
    /**
     * Test Zero Transaction Amount.
     */
    @Test
    @SuppressWarnings("static-access")
    public void testZeroTransactionDate() {
        Transaction t = new Transaction(0.0, Transaction.DEPOSIT);        
        Date now = dateProvider.now();
        
        assertEquals(dateProvider.makeDateTimeStr(t.getTransactionDate()), dateProvider.makeDateTimeStr(now));
    }
    
    
    /**
     * Test Zero Transaction Amount 
     */
    @Test
    public void testZeroTransactionAmount() {
        Transaction t = new Transaction(0.0, Transaction.DEPOSIT);
             
        assertEquals(0.0, t.getTransactionAmount(), DOUBLE_DELTA);
    }
}
