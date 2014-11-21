package com.abc;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class AccountTest {

	private static final double DOUBLE_DELTA = 1e-15;
	
	@Test
	public void testNegativeAndZeroWithdraw() {
		String errorTxt = null;
		Account checkingAccount = new Account(Account.MAXI_SAVINGS, "Account-1");
		try {
			
			checkingAccount.withdraw(-5.0);
			
		}catch (IllegalArgumentException e){
			errorTxt = e.getMessage();
		}
		
		assertEquals("amount must be greater than zero", errorTxt);
		
	    errorTxt = null;
       try {
			
			checkingAccount.withdraw(0.0);
			
		}catch (IllegalArgumentException e){
			errorTxt = e.getMessage();
		}
		
		assertEquals("amount must be greater than zero", errorTxt);
	}
	
	@Test
	public void testNegativeAndZeroDeposit() {
		String errorTxt = null;
		Account checkingAccount = new Account(Account.MAXI_SAVINGS,   "Account-1");
		try {
			
			checkingAccount.deposit(-5.0);
			
		}catch (IllegalArgumentException e){
			errorTxt = e.getMessage();
		}
		
		assertEquals("amount must be greater than zero", errorTxt);
		
	    errorTxt = null;
       try {
			
			checkingAccount.deposit(0.0);
			
		}catch (IllegalArgumentException e){
			errorTxt = e.getMessage();
		}
		
		assertEquals("amount must be greater than zero", errorTxt);
	}
	
	
	@Test
	public void testWithdraw() {
		Account checkingAccount = new Account(Account.MAXI_SAVINGS, "Account-1");
		checkingAccount.deposit(10.0);		
		checkingAccount.withdraw(5.0);
		
		assertEquals("Expected -5.0", -5.0, checkingAccount.getTransactions().get(1).amount, DOUBLE_DELTA);
	}
	
	@Test
	public void testWithdrawWithoutFund() {
		Account checkingAccount = new Account(Account.MAXI_SAVINGS, "Account-1");
		String errorTxt = null;
		try {

			checkingAccount.withdraw(5.0);
		} catch (IllegalArgumentException e) {
			errorTxt = e.getMessage();
		}

		assertEquals("Not enough fund to withdraw.", errorTxt);
	}
	
	@Test
	public void testDeposit() {
		Account checkingAccount = new Account(Account.MAXI_SAVINGS, "Account-1");
		checkingAccount.deposit(5.0);
		
		assertEquals("Expected -5.0", 5.0, checkingAccount.getTransactions().get(0).amount, DOUBLE_DELTA);
	}
	
	@Test
	public void testCheckinginterestEarned() {
		Account checkingAccount = new Account(Account.CHECKING,  "Account-1");
		checkingAccount.deposit(1000.0);
		
		assertEquals("Expected 1.0", 1.0, checkingAccount.interestEarned(), DOUBLE_DELTA);
		
		checkingAccount.deposit(1000.0);
		
		assertEquals("Expected 2.0", 2.0, checkingAccount.interestEarned(), DOUBLE_DELTA);
	}
	
	@Test
	public void testSavingterestEarned() {
		Account checkingAccount = new Account(Account.SAVINGS, "Account-1");
		checkingAccount.deposit(1000.0);
		
		assertEquals("Expected 1.0", 1.0, checkingAccount.interestEarned(), DOUBLE_DELTA);
		
		checkingAccount.deposit(1000.0);
		
		assertEquals("Expected 3.0", 3.0, checkingAccount.interestEarned(), DOUBLE_DELTA);
	}
	
	@Test
	public void testMaxiSavingterestEarned() {
		Account checkingAccount = new Account(Account.MAXI_SAVINGS,  "Account-1");
		checkingAccount.deposit(1000.0);
		
		assertEquals("Expected 20.0", 20.0, checkingAccount.interestEarned(), DOUBLE_DELTA);
		
		checkingAccount.deposit(1000.0);
		
		assertEquals("Expected 70.0", 70.0, checkingAccount.interestEarned(), DOUBLE_DELTA);
		
        checkingAccount.deposit(1000.0);
		
		assertEquals("Expected 170.0", 170.0, checkingAccount.interestEarned(), DOUBLE_DELTA);
	}
}
