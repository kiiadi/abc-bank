package com.abc;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.abc.BankConstants.AccountType;
import com.abc.BankConstants.TransactionType;

public class AccountTest {
	@Test 
	public void testAccountCreationAccount()
	{
	    Account checkingAccount = new Account(AccountType.CHECKING);
	    assertEquals(AccountType.CHECKING, checkingAccount.getAccountType());
	    assertTrue(checkingAccount.getInterestCalculator().getClass() == CheckingInterestCalculator.class );
	    assertEquals(0,checkingAccount.getAmount(),BankTestConstants.DOUBLE_DELTA );
	    
	    Account savingsAccount = new Account(AccountType.SAVINGS);
	    assertEquals(AccountType.SAVINGS, savingsAccount.getAccountType());
	    assertTrue(savingsAccount.getInterestCalculator().getClass() == SavingInterestCalculator.class );
	    assertEquals(0,savingsAccount.getAmount(),BankTestConstants.DOUBLE_DELTA );
	    
        Account maxiSavingAccount = new Account(AccountType.MAXI_SAVINGS);
        assertEquals(AccountType.MAXI_SAVINGS, maxiSavingAccount.getAccountType());
	    assertTrue(maxiSavingAccount.getInterestCalculator().getClass() == MaxiSavingInterestCalculator.class );
	    assertEquals(0,maxiSavingAccount.getAmount(),BankTestConstants.DOUBLE_DELTA );
	 }
	
	
	@Test
	public void testDepositPositiveAmount()
	{
		Account checkingAccount = new Account(AccountType.CHECKING);
		checkingAccount.deposit(100);
		assertEquals(100,checkingAccount.getAmount(),BankTestConstants.DOUBLE_DELTA );
		assertTrue(checkingAccount.getTransactions().size() == 1 );
		assertTrue(checkingAccount.getTransactions().get(0).getTransactionType() == TransactionType.DEPOSIT );
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void testDepositNegativeAmount()
	{
		Account checkingAccount = new Account(AccountType.CHECKING);
		checkingAccount.deposit(-100);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void testWithdrawalNegativeAmount()
	{
		Account checkingAccount = new Account(AccountType.CHECKING);
		checkingAccount.withdraw(-100);
	}
	
	public void testWithdrawalInLimitAmount()
	{
		Account checkingAccount = new Account(AccountType.CHECKING);
		checkingAccount.deposit(100);
		checkingAccount.withdraw(10);
		assertEquals(90,checkingAccount.getAmount(),BankTestConstants.DOUBLE_DELTA );
		assertTrue(checkingAccount.getTransactions().size() == 2 );
		assertTrue(checkingAccount.getTransactions().get(1).getTransactionType() == TransactionType.WITHDRAWAL);
	}
	public void testWithdrawalAllAmount()
	{
		Account checkingAccount = new Account(AccountType.CHECKING);
		checkingAccount.deposit(100);
		checkingAccount.withdraw(100);
		assertEquals(0,checkingAccount.getAmount(),BankTestConstants.DOUBLE_DELTA );
		assertTrue(checkingAccount.getTransactions().size() == 2 );
		assertTrue(checkingAccount.getTransactions().get(1).getTransactionType() == TransactionType.WITHDRAWAL);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void testWithdrawalExceededAmount()
	{
		Account checkingAccount = new Account(AccountType.CHECKING);
		checkingAccount.deposit(100);
		checkingAccount.withdraw(500);
	}
	
	@Test 
	public void testApplyInterest()
	{
		Account checkingAccount = new Account(AccountType.CHECKING);
		checkingAccount.deposit(365);
		checkingAccount.applyInterest();
		assertEquals(365.001,checkingAccount.getAmount(),BankTestConstants.DOUBLE_DELTA );
		assertEquals(0.001,checkingAccount.interestEarned(),BankTestConstants.DOUBLE_DELTA );
		assertTrue(checkingAccount.getTransactions().size() == 2 );
		assertTrue(checkingAccount.getTransactions().get(0).getTransactionType() == TransactionType.DEPOSIT );
		assertTrue(checkingAccount.getTransactions().get(1).getTransactionType() == TransactionType.INTEREST );
	}

}
