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
public class SavingsAccountTest {
	private SavingsAccount account;

	@Before
	public void setUp() throws Exception {
		account = new SavingsAccount();
	}

	@Test
	public void getAccountType_ForSavingsAccount_AccountTypeIsSet() {
		assertNotNull(account.getAccountType());
		assertEquals(account.getAccountType(), AccountType.SAVINGS_ACCOUNT);
	}
	
	@Test
	public void interestEarned_ForFirstThousandBalance_InterestRateIsPointOnePercent()
			throws InvalidTransactionAmountException {
		account.deposit(1000);
		
		assertTrue(account.interestEarned() == 1);
		
		account.getTransactions().clear();
	}
	
	@Test
	public void interestEarned_ForBalanceGreaterThanThousand_InterestRateIsPointTwoPercent()
			throws InvalidTransactionAmountException, InsufficientFundsException {
		account.deposit(1000);
		account.deposit(2000);
		account.withdraw(1000);
		
		assertTrue(account.interestEarned() == 3);
		
		account.getTransactions().clear();
	}
	
	@Test
	public void toString_ForSavingsAccount_ToStringIsOverriden() {
				
		assertTrue(account.toString().equals(AccountType.SAVINGS_ACCOUNT.getName())); 
	}
}
