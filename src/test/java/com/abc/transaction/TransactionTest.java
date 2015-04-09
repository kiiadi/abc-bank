package com.abc.transaction;

import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.junit.Test;

import com.abc.exception.InvalidTransactionException;

public class TransactionTest {
    
	@Test
    public void testDepositTransaction() throws InvalidTransactionException {
		BigDecimal currentBalance = new BigDecimal(9000D);
        Transaction t = new Deposit(new BigDecimal(1000D));
        BigDecimal updatedBalance = t.process(currentBalance);
        
        assertTrue(t instanceof Deposit);
        assertEquals(t.getTransactionType(), TransactionType.DEPOSIT.getDisplayName());
        assertTrue(new BigDecimal(10000D).compareTo(updatedBalance) == 0);
    }
	
	@Test
    public void testDepositWithdraw() throws InvalidTransactionException {
		BigDecimal currentBalance = new BigDecimal(9000D);
        Transaction t = new Withdraw(new BigDecimal(1000D));
        BigDecimal updatedBalance = t.process(currentBalance);
        
        assertTrue(t instanceof Withdraw);
        assertEquals(t.getTransactionType(), TransactionType.WITHDRAW.getDisplayName());
        assertTrue(new BigDecimal(8000D).compareTo(updatedBalance) == 0);
    }
	
	@Test
    public void testGetStatement() throws ParseException {
        Transaction t = new Deposit(new BigDecimal(1000D));
        t.setTransDate(new SimpleDateFormat("yyyy/MM/dd").parse("2015/01/01"));
        
        assertEquals("2015-01-01	Deposit	$1,000.00", t.getStatementListing());
    }

}
