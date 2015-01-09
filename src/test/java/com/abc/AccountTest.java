package com.abc;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class AccountTest {
	private Account anyAccount;
	
	@Before
	public void givenAccounts() {
		anyAccount = new Account(null) {
			@Override
			public double interestEarned() {
				throw new RuntimeException();
			}
		};
	}
	
	@Test
	public void givenAnyAccount_whenDeposite_thenAddTransactionWithPositiveAmount() {
		anyAccount.deposit(10.0);
		assertEquals(1, anyAccount.getTransactions().size());
		assertAmount(10.0, anyAccount.getTransactions().get(0).getAmount());
	}
	
	@Test
	public void givenAnyAccount_whenWithdraw_thenAddTransactionWithNegativeAmount() {
		anyAccount.withdraw(10.0);
		assertEquals(1, anyAccount.getTransactions().size());
		assertAmount(-10.0, anyAccount.getTransactions().get(0).getAmount());
	}
	
	private void assertAmount(double expect, double actual) {
	    assertEquals(expect, actual, 1e-15);
	}
}
