package com.abc;

import static org.junit.Assert.*;

import org.junit.Test;

public class AccountTest {

	private static final double DOUBLE_DELTA = 0.0099;
	
	@Test
	public void amountDepositTest()
	{
		Account account = new Account(Account.CHECKING);
		account.deposit(1000);
		assertEquals(1000.00, account.sumTransactions(),0);
	}

	@Test
	public void amountWithDrawTest()
	{
		Account account = new Account(Account.CHECKING);
		account.deposit(1000);
		account.withdraw(20);
		assertEquals(980.00, account.sumTransactions(),0);
		
		
		Account account1 = new Account(Account.SAVINGS);
		account1.deposit(1000);
		account1.withdraw(1020);
		assertEquals(-20.00, account1.sumTransactions(),0);
	}
	
	@Test
	public void accountBalanceTest()
	{
		Account account1 = new Account(Account.CHECKING);
		account1.deposit(1000);
		assertEquals(1000,account1.sumTransactions(),0);
		
		Account account2 = new Account(Account.SAVINGS);
		account2.deposit(1000);
		account2.deposit(200);
		account2.deposit(20);
		assertEquals(1220,account2.sumTransactions(),0);
		
		Account account3 = new Account(Account.MAXI_SAVINGS);
		account3.deposit(1000);
		account3.withdraw(200);
		account3.deposit(20);
		assertEquals(820,account3.sumTransactions(),0);
		
		Account account4 = new Account(Account.CHECKING);
		account4.deposit(1000);
		account4.withdraw(1000);
		assertEquals(0,account4.sumTransactions(),0);
		
	}
	
	@Test
	public void interestEarnedTest()
	{
		Account account1 = new Account(Account.CHECKING);
		account1.deposit(1000);
		assertEquals(1.00,account1.interestEarned(),0);
		
		Account account2 = new Account(Account.SAVINGS);
		account2.deposit(1000);
		assertEquals(1.00,account2.interestEarned(),0);
		
		Account account2A = new Account(Account.SAVINGS);
		account2A.deposit(2000);
		assertEquals(3.00,account2A.interestEarned(),0);
		
		Account account3 = new Account(Account.MAXI_SAVINGS);
		account3.deposit(1000);
		assertEquals(50.00,account3.interestEarned(),0);

		Account account4 = new Account(Account.MAXI_SAVINGS);
		account4.deposit(1000);
		account4.withdraw(100);
		assertEquals(0.9,account4.interestEarned(),0);
	}
	
	@Test
	public void interestEarnedDailyAccrual()
	{
		Account account1 = new Account(Account.MAXI_SAVINGS);
		account1.deposit(1000000);
		assertEquals(136.98,account1.getDailyInterestAccrual(),DOUBLE_DELTA);
	}
}
