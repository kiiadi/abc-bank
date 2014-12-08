package com.abc;

import static org.junit.Assert.*;


import org.junit.Ignore;
import org.junit.Test;

public class CustomerTest {

	private static final double DOUBLE_DELTA = 0.0099;
	
	 @Test //Test customer statement generation 
	    public void testApp(){ 
	        Account checkingAccount = new Account(Account.CHECKING); 
	        Account savingsAccount = new Account(Account.SAVINGS); 
	        Customer henry = new Customer("Henry").openAccount(checkingAccount).openAccount(savingsAccount); 
	        checkingAccount.deposit(100.0); 
	        savingsAccount.deposit(4000.0); 
	        savingsAccount.withdraw(200.0); 
	        assertEquals("Statement for Henry\n" + 
	                "\n" + 
	                "Checking Account\n" + 
	                "  deposit $100.00\n" + 
	                "Total $100.00\n" + 
	                "\n" + 
	                "Savings Account\n" + 
	                "  deposit $4,000.00\n" + 
	                "  withdrawal $200.00\n" + 
	                "Total $3,800.00\n" + 
	                "\n" + 
	                "Total In All Accounts $3,900.00", henry.getStatement()); 
	      //  System.out.println(henry.getStatement());
	    } 

	    @Test 

	    public void testOneAccount(){ 
	        Customer oscar = new Customer("Oscar").openAccount(new Account(Account.SAVINGS)); 
	        assertEquals(1, oscar.getNumberOfAccounts()); 
	    } 

	    @Test 
	    public void testTwoAccount(){ 
	        Customer oscar = new Customer("Oscar") 
	                .openAccount(new Account(Account.SAVINGS)); 
	        oscar.openAccount(new Account(Account.CHECKING)); 
	        assertEquals(2, oscar.getNumberOfAccounts()); 
	    } 

	    @Test
	    public void testTransferFunds(){
	    	Account checkingAccount = new Account(Account.CHECKING); 
	        Account savingsAccount = new Account(Account.SAVINGS); 
	        Customer john = new Customer("John").openAccount(checkingAccount).openAccount(savingsAccount); 
	        checkingAccount.deposit(100.0); 
	        savingsAccount.deposit(4000.0); 
	        savingsAccount.withdraw(200.0); 
	        assertEquals("Statement for John\n" + 
	                "\n" + 
	                "Checking Account\n" + 
	                "  deposit $100.00\n" + 
	                "Total $100.00\n" + 
	                "\n" + 
	                "Savings Account\n" + 
	                "  deposit $4,000.00\n" + 
	                "  withdrawal $200.00\n" + 
	                "Total $3,800.00\n" + 
	                "\n" + 
	                "Total In All Accounts $3,900.00", john.getStatement()); 
	       // System.out.println("Before Transfer: " + john.getStatement());
	        assertTrue(john.transferFunds(savingsAccount,checkingAccount,2000.00));
	       // System.out.println("After Transfer: " + john.getStatement());
	    }
	    
	    
	    
	    
	    @Test
	    public void testDailyInterstAccrual(){
	    	Account checkingAccount = new Account(Account.CHECKING); 
	        Account savingsAccount = new Account(Account.SAVINGS); 
	        Customer john = new Customer("John").openAccount(checkingAccount).openAccount(savingsAccount); 
	        checkingAccount.deposit(1000000.0); 
	        savingsAccount.deposit(4000000.0); 
	        savingsAccount.withdraw(200.0); 
	        assertEquals(24.65,john.totalDailyInterestAccrued(),DOUBLE_DELTA);
	        
	    }
	    
	    @Ignore 
	    public void testThreeAcounts() { 
	        Customer oscar = new Customer("Oscar") 
	                .openAccount(new Account(Account.SAVINGS)); 
	        oscar.openAccount(new Account(Account.CHECKING)); 
	        assertEquals(3, oscar.getNumberOfAccounts()); 
	    } 


}
