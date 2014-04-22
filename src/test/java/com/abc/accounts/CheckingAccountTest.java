package com.abc.accounts;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.abc.exceptions.InsufficientFundsException;
import com.abc.exceptions.InvalidTransactionAmountException;

/**
 * Test-cases follow the following method-name pattern:
 * methodName_Scenario_ExpectedResult()
 *
 */
public class CheckingAccountTest {
	private CheckingAccount account;

	@Before
	public void setUp() throws Exception {
		account = new CheckingAccount();
	}

	@Test
	public void getAccountType_ForCheckingAccount_AccountTypeIsSet() {
		assertNotNull(account.getAccountType());
		assertEquals(account.getAccountType(), AccountType.CHECKING_ACCOUNT);
	}
	
	@Test
	public void interestEarned_ForCheckingAccount_InterestRateIsPointOnePercent() throws InvalidTransactionAmountException {
		account.deposit(1000);
		
		assertTrue(account.interestEarned() == 1);
		
		account.getTransactions().clear();
	}
	
	@Test
	public void interestEarned_ForCheckingAccountWithMultipleTransactions_InterestRateIsPointOnePercent()
			throws InvalidTransactionAmountException, InsufficientFundsException {
		account.deposit(1000);
		account.deposit(2000);
		account.withdraw(1000);
		
		assertTrue(account.interestEarned() == 2);
		
		account.getTransactions().clear();
	}
	
	@Test
	public void toString_ForCheckingAccount_ToStringIsOverriden() {
				
		assertTrue(account.toString().equals(AccountType.CHECKING_ACCOUNT.getName())); 
	}
}
