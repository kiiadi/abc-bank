package com.abc;

import org.junit.Test;

import static org.junit.Assert.*;

import java.util.Calendar;
import java.util.List;

public class TransactionTest {
    @Test
    public void transaction() {
        Transaction t = new Transaction(5);
        assertTrue(t instanceof Transaction);
    }
    
    @Test
    public void testGetterMethods(){
    	 Account checkingAccount = new Account(Account.CHECKING);
    	 Customer henry = new Customer("Henry").openAccount(checkingAccount);
    	 checkingAccount.deposit(100.0);
    	 checkingAccount.withdraw(10);
    	 List<Transaction> t= henry.getAccounts().get(henry.getAccounts().size()-1).transactions;
    	
    	 Transaction first = t.get(0);
    	 assertEquals(100, first.getAmount(),0.0);
    	 Calendar cal = Calendar.getInstance();
    	 cal.setTime(first.getTranscationDate());
    	 assertEquals(2016, cal.get(Calendar.YEAR));
    	 
    }
}
