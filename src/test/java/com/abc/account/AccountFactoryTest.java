package com.abc.account;

import org.junit.Assert;

import org.junit.Test;

public class AccountFactoryTest {

	@Test
	public void testCreateChecking() {
		AccountFactory f = new AccountFactory();
		Account a = f.create(AccountType.CHECKING);
		Assert.assertTrue(a instanceof CheckingAccount);
		Assert.assertEquals(AccountType.CHECKING, a.getAccountType());
	}

	@Test
	public void testCreateSavings() {
		AccountFactory f = new AccountFactory();
		Account a = f.create(AccountType.SAVINGS);
		Assert.assertTrue(a instanceof SavingsAccount);
		Assert.assertEquals(AccountType.SAVINGS, a.getAccountType());
		
	}

	@Test
	public void testCreateMaxiSavings() {
		AccountFactory f = new AccountFactory();
		Account a = f.create(AccountType.MAXI_SAVINGS);
		Assert.assertTrue(a instanceof MaxiSavingsAccount);
		Assert.assertEquals(AccountType.MAXI_SAVINGS, a.getAccountType());
		
	}

}
