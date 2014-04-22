package com.abc.accounts;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

/**
 * Test-cases follow the following method-name pattern:
 * methodName_Scenario_ExpectedResult()
 *
 */
public class AccountFactoryTest {

	@Test
	public void createAccount_NullAccountTypeIsPassed_NullIsReturned() {
		Account account = AccountFactory.createAccount(null);
		
		assertNull(account);
	}
	
	@Test
	public void createAccount_CheckingAccountTypeIsPassed_CheckingAccountInstanceIsCreated() {
		Account account = AccountFactory.createAccount(AccountType.CHECKING_ACCOUNT);
		
		assertTrue(account instanceof CheckingAccount);
		assertTrue(account.getAccountType().equals(AccountType.CHECKING_ACCOUNT));
	}

	@Test
	public void createAccount_SavingsAccountTypeIsPassed_SavingsAccountInstanceIsCreated() {
		Account account = AccountFactory.createAccount(AccountType.SAVINGS_ACCOUNT);
		
		assertTrue(account instanceof SavingsAccount);
		assertTrue(account.getAccountType().equals(AccountType.SAVINGS_ACCOUNT));
	}
	
	@Test
	public void createAccount_MaxiSavingsAccountTypeIsPassed_MaxiSavingsAccountInstanceIsCreated() {
		Account account = AccountFactory.createAccount(AccountType.MAXI_SAVINGS_ACCOUNT);
		
		assertTrue(account instanceof MaxiSavingsAccount);
		assertTrue(account.getAccountType().equals(AccountType.MAXI_SAVINGS_ACCOUNT));
	}
}
