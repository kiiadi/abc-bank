package com.abc;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * User: mchernyak
 * Date: 2/22/14
 * Time: 12:25 PM
 */
public class AccountFactoryTest {

	@Test
	public void testCreateSavingsAccount() {
		Account account = AccountFactory.createAccount(AccountType.SAVINGS);
		assertTrue(account instanceof SavingsAccount);
		assertEquals("Savings Account", account.getTypeName());
	}

	@Test
	public void testCreateCheckingAccount() {
		Account account = AccountFactory.createAccount(AccountType.CHECKING);
		assertTrue(account instanceof CheckingAccount);
		assertEquals("Checking Account", account.getTypeName());
	}

	@Test
	public void testCreateMaxiSavingsAccount() {
		Account account = AccountFactory.createAccount(AccountType.MAXI_SAVINGS);
		assertTrue(account instanceof MaxiSavingsAccount);
		assertEquals("Maxi Savings Account", account.getTypeName());
	}
}
