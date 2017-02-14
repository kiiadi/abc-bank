package com.abc;

import org.junit.Test;

import com.abc.Transaction.TRANSACTION_TYPE;

import static org.junit.Assert.assertTrue;

import java.time.Instant;

public class TransactionTest {
	
    @Test
    public void transactionDepositOK() {
    	Instant now = Instant.now();
    	Double amount = 33.33d;
        Transaction t = new Transaction(now, amount);
        
        assertTrue(now.equals(t.getTransactionInstant()));
        assertTrue(amount.equals(t.getAmount()));
        assertTrue(TRANSACTION_TYPE.DEPOSIT.equals(t.getTransactionType()));
    }

    @Test
    public void transactionWithdrawOK() {
    	Instant now = Instant.now();
    	Double amount = -22.22d;
        Transaction t = new Transaction(now, amount);
        
        assertTrue(now.equals(t.getTransactionInstant()));
        assertTrue(amount.equals(t.getAmount()));
        assertTrue(TRANSACTION_TYPE.WITHDRAW.equals(t.getTransactionType()));
    }

    @Test(expected=IllegalArgumentException.class)
    public void transactionException() {
    	Instant now = Instant.now();
    	Double amount = 0.0d;
        Transaction t = new Transaction(now, amount);
    }

}
