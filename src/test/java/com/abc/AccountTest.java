package com.abc;

import java.util.Calendar;
import java.util.Date;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class AccountTest {

    private static final double DOUBLE_DELTA = 1e-13;
    
    @Test
    public void testCalTotalMaxiSavingsAccount() {
    	Account maxiSavingsAccount = new MaxiSavingsAccount();
    	maxiSavingsAccount.deposit(100);
    	maxiSavingsAccount.withdraw(50);
        
    	assertEquals(50.0, maxiSavingsAccount.calculateTotal(), DOUBLE_DELTA);
    }
    
    @Test
    public void testSumTransaction() {
    	Account maxiSavingsAccount = new MaxiSavingsAccount();
    	maxiSavingsAccount.deposit(100);
    	maxiSavingsAccount.withdraw(30);
        
    	assertEquals(70, maxiSavingsAccount.sumTransactions(), DOUBLE_DELTA);
    }
    
    @Test
    public void testCalTotalCheckingAccount() {
    	Account checkingAccount = new CheckingAccount();
    	Transaction t = new Deposit(100);
    	t.setDate(getTestDate(-4));
    	checkingAccount.addTransaction(t);
    	checkingAccount.deposit(100);
    	checkingAccount.withdraw(52);
    	
        double expected = 48 + 100 * Math.pow(1+0.001/365, 4);
    	assertEquals(expected, checkingAccount.calculateTotal(), DOUBLE_DELTA);
    }
      
    @Test
    public void testTotalInterestEarned() {
    	Account maxiSavingsAccount = new MaxiSavingsAccount();
    	Transaction t1 = new Deposit(100);
    	t1.setDate(getTestDate(-3));
    	Transaction t2 = new Withdraw(20);
    	t2.setDate(getTestDate(-1));
    	maxiSavingsAccount.addTransaction(t1);
    	maxiSavingsAccount.addTransaction(t2);
    	double expected = 100.0 * Math.pow((1+0.05/365), 2);
    	expected = (expected-20) * Math.pow((1+0.001/365), 1);
    	assertEquals(expected, maxiSavingsAccount.calculateTotal(), DOUBLE_DELTA); 	
    }   

    @Test
    public void testTotalInterestEarnedSavingsAccount() {
    	Account savingsAccount = new SavingsAccount();
    	Transaction t1 = new Deposit(100);
    	t1.setDate(getTestDate(-3));
    	Transaction t2 = new Withdraw(20);
    	t2.setDate(getTestDate(-1));
    	savingsAccount.addTransaction(t1);
    	savingsAccount.addTransaction(t2);
    	double expected = 100.0 * Math.pow((1+0.001/365), 2);
    	expected = (expected-20) * Math.pow((1+0.001/365), 1);
    	assertEquals(expected, savingsAccount.calculateTotal(), DOUBLE_DELTA); 	
    }
    
    // reverse the order to add transaction.
    @Test
    public void testTotalInterestEarnedSavingsAccount1() {
    	Account savingsAccount1 = new SavingsAccount();
    	Transaction t1 = new Deposit(100);
    	t1.setDate(getTestDate(-3));
    	Transaction t2 = new Withdraw(20);
    	t2.setDate(getTestDate(-1));
     	savingsAccount1.addTransaction(t2);
       	savingsAccount1.addTransaction(t1);
       	Account savingsAccount2 = new SavingsAccount();
       	savingsAccount2.addTransaction(t1);
       	savingsAccount2.addTransaction(t2);
    	assertEquals(0, savingsAccount1.calculateTotal() - savingsAccount2.calculateTotal(), DOUBLE_DELTA); 	
    }
    
    @Test
    public void testTotalInterestEarnedSavingsAccount2() {
    	Account savingsAccount = new SavingsAccount();
    	Transaction t1 = new Deposit(10050.0);
    	t1.setDate(getTestDate(-1));
    	savingsAccount.addTransaction(t1);
    	double expected = 10050.0 + 1000.0 * 0.001/365.0 + 9050.0 * 0.002/365.0;
    	assertEquals(expected, savingsAccount.calculateTotal(), DOUBLE_DELTA); 	
    }

    //help function for testing purpose
    private Date getTestDate(int numberOfDays) {
    	Calendar c = Calendar.getInstance();
    	c.add(Calendar.DATE, numberOfDays);
        return c.getTime();
    }
}
