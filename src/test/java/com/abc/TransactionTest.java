package com.abc;

import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class TransactionTest {

	@Test
    public void testCreate() {
    	
    	Account acct = new CheckingAccount();
        Transaction t = new DepositTransaction(acct, 5);
        assertTrue(t instanceof Transaction);
    }
	
}
