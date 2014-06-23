package com.abc;

import org.junit.Test;

public class AccountTest {  
    @Test(expected = IllegalArgumentException.class) //Test over withdraw 
    public void testWithDrawMoreThanBalance(){
        Account savingsAccount = new Account(Account.SAVINGS);
        savingsAccount.deposit(4000.0, null); 
       	savingsAccount.withdraw(4800.0, null);
    }
    
    @Test(expected = IllegalArgumentException.class) //Test deposit negative amount 
    public void testDepositeNegativeAmount(){
        Account savingsAccount = new Account(Account.SAVINGS);
        savingsAccount.deposit(-4000.0, null); 
    }
    
	@Test(expected = IllegalArgumentException.class)
	// Test withdraw negative amount
	public void testWithDrawNegativeAmount() {
		Account savingsAccount = new Account(Account.SAVINGS);
		savingsAccount.withdraw(-4000.0, null);
	}
}
