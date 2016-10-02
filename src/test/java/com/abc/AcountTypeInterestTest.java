package com.abc;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import org.junit.Test;

public class AcountTypeInterestTest {
	
	private static final double DOUBLE_DELTA = 0.01;
	
	@Test
    public void checkingAccount() {
        
    	
		List<Transaction> transactions = new ArrayList<Transaction>();
		Date d20 = daysBeforeToday(20);
		transactions.add(new Transaction(2000000.0, d20));
		Date d10 = daysBeforeToday(10);
		transactions.add(new Transaction(1000000.0, d10));
		
        assertEquals(136.99, AccountType.Checking.interestEarned(transactions), DOUBLE_DELTA);
    }
	
	@Test
    public void checkingAccountWithSamedayTransactions() {
        
    	
		List<Transaction> transactions = new ArrayList<Transaction>();
		Date d20 = daysBeforeToday(20);
		transactions.add(new Transaction(1000000.0, d20));
		transactions.add(new Transaction(1000000.0, d20));
		Date d10 = daysBeforeToday(10);
		transactions.add(new Transaction(500000.0, d10));
		transactions.add(new Transaction(500000.0, d10));
		
        assertEquals(136.99, AccountType.Checking.interestEarned(transactions), DOUBLE_DELTA);
    }
	
	@Test
    public void checkingAccountReverse() {
        
    	
		List<Transaction> transactions = new ArrayList<Transaction>();
		Date d10 = daysBeforeToday(10);
		transactions.add(new Transaction(1000000.0, d10));
		Date d20 = daysBeforeToday(20);
		transactions.add(new Transaction(2000000.0, d20));
		
        assertEquals(136.99, AccountType.Checking.interestEarned(transactions), DOUBLE_DELTA);
    }
	
	@Test
    public void savingAccount() {
        
    	
		List<Transaction> transactions = new ArrayList<Transaction>();
		Date d20 = daysBeforeToday(20);
		transactions.add(new Transaction(2000000.0, d20));
		Date d10 = daysBeforeToday(10);
		transactions.add(new Transaction(1000000.0, d10));
		
        assertEquals(273.97, AccountType.Savings.interestEarned(transactions), DOUBLE_DELTA);
    }
	
	@Test
    public void savingAccountTwoRates() {
        
    	
		List<Transaction> transactions = new ArrayList<Transaction>();
		Date d20 = daysBeforeToday(20);
		transactions.add(new Transaction(500.0, d20));
		Date d10 = daysBeforeToday(10);
		transactions.add(new Transaction(1000.0, d10));
		
        assertEquals(0.09, AccountType.Savings.interestEarned(transactions), DOUBLE_DELTA);
    }
	
	@Test
    public void maxiSavingAccount() {
        
    	
		List<Transaction> transactions = new ArrayList<Transaction>();
		Date d20 = daysBeforeToday(20);
		transactions.add(new Transaction(2000.0, d20));
		Date d10 = daysBeforeToday(10);
		transactions.add(new Transaction(1000.0, d10));
		
        assertEquals(6.85, AccountType.Max_Savings.interestEarned(transactions), DOUBLE_DELTA);
    }
	
	@Test
    public void maxiSavingAccountWithWithdraw() {
        
    	
		List<Transaction> transactions = new ArrayList<Transaction>();
		Date d20 = daysBeforeToday(20);
		transactions.add(new Transaction(2000000.0, d20));
		Date d15 = daysBeforeToday(15);
		transactions.add(new Transaction(-1000000.0, d15));
		Date d10 = daysBeforeToday(10);
		transactions.add(new Transaction(1000000.0, d10));
		
        assertEquals(2780.82, AccountType.Max_Savings.interestEarned(transactions), DOUBLE_DELTA);
    }

    private Date daysBeforeToday(int days) {
    	
    	Date today = DateProvider.getInstance().now();
    	GregorianCalendar c = new GregorianCalendar();
		c.setTime(today);
		c.add(Calendar.DAY_OF_MONTH, -days);
		return c.getTime();
    	
    }
}
