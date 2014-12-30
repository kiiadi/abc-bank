package com.abc;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

import com.abc.Account.AccountType;

public class AccountTest {
    private static final double DOUBLE_DELTA = 1e-15;

	@Test(expected=IllegalArgumentException.class)
    public void testDepositZero() {
		Account account = new Account(AccountType.CHECKING);
		 account.deposit(0);
    }

	@Test(expected=IllegalArgumentException.class)
    public void testWithdrawZero() {
		Account account = new Account(AccountType.CHECKING);
		 account.withdraw(0);
    }

	@Test
    public void testInterestEarnedChecking() {
		Account account = new Account(AccountType.CHECKING);
		assertEquals(0, account.interestEarned(), DOUBLE_DELTA);
		
		account.deposit(1000);
		assertEquals(1.0, account.interestEarned(), DOUBLE_DELTA);
		
		account.deposit(2000);
		assertEquals(3.0, account.interestEarned(), DOUBLE_DELTA);
		
		account.deposit(3000);
		assertEquals(6.0, account.interestEarned(), DOUBLE_DELTA);
		
    }

	@Test
    public void testInterestEarnedSaving() {
		Account account2 = new Account(AccountType.SAVINGS);
		assertEquals(0, account2.interestEarned(), DOUBLE_DELTA);
		
		account2.deposit(1000);
		assertEquals(1.0, account2.interestEarned(), DOUBLE_DELTA);
		
		account2.deposit(2000);
		assertEquals(5.0, account2.interestEarned(), DOUBLE_DELTA);
		
		account2.deposit(3000);
		assertEquals(11.0, account2.interestEarned(), DOUBLE_DELTA);
		
    }

	@Test
    public void testInterestEarnedMaxiSaving() {
		Account account3 = new Account(AccountType.MAXI_SAVINGS);
		assertEquals(0, account3.interestEarned(), DOUBLE_DELTA);
		
		account3.deposit(1000);
		assertEquals(20.0, account3.interestEarned(), DOUBLE_DELTA);
		
		account3.deposit(1000);
		assertEquals(70.0, account3.interestEarned(), DOUBLE_DELTA);
		
		account3.deposit(3000);
		assertEquals(370.0, account3.interestEarned(), DOUBLE_DELTA);
		
    }

	
}
