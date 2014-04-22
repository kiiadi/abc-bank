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
public class MaxiSavingsAccountTest {
	private MaxiSavingsAccount account;

	@Before
	public void setUp() throws Exception {
		account = new MaxiSavingsAccount();
	}

	@Test
	public void getAccountType_ForMaxiSavingsAccount_AccountTypeIsSet() {
		assertNotNull(account.getAccountType());
		assertEquals(account.getAccountType(), AccountType.MAXI_SAVINGS_ACCOUNT);
	}
	
	@Test
	public void interestEarned_ForBalanceWithoutWithdrawalThePastTenDays_InterestRateIsFivePercent()
			throws InvalidTransactionAmountException {
		account.deposit(2000);
		account.deposit(1000);
		
		assertTrue(account.interestEarned() == 150);
		
		account.getTransactions().clear();
	}
	
	@Test
	public void interestEarned_ForBalanceWithWithdrawalThePastTenDays_InterestRateIsPointOnePercent()
			throws InvalidTransactionAmountException, InsufficientFundsException {
		account.deposit(1000);
		account.deposit(2000);
		account.withdraw(1000);
		
		assertTrue(account.interestEarned() == 2);
		
		account.getTransactions().clear();
	}
	
	@Test
	public void toString_ForMaxiSavingsAccount_ToStringIsOverriden() {
				
		assertTrue(account.toString().equals(AccountType.MAXI_SAVINGS_ACCOUNT.getName())); 
	}

}
