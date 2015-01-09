package com.abc;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class AccountTest {
	private Account anyAccount, anyAccount2, from, to;
	
	@Before
	public void givenAccounts() {
		anyAccount = createAnyAccount();
		anyAccount2 = createAnyAccount();
		from = createAnyAccount();
		to = createAnyAccount();
	}

	private Account createAnyAccount() {
		return new Account(null) {
			@Override
			public double interestEarned() {
				throw new RuntimeException();
			}
		};
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void givenAnyAccount_whenDepositeNegativeAmount_thenRaiseException() {
		anyAccount.deposit(-10.0);
	}
	@Test(expected=IllegalArgumentException.class)
	public void givenAnyAccount_whenWithdrawNegativeAmount_thenRaiseException() {
		anyAccount.deposit(-10.0);
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

	@Test(expected=IllegalArgumentException.class)
	public void givenTwoAccountsOfDifferentCustomers_whenTransfer_thenRaiseException() {
		new Customer("John").openAccount(anyAccount);
		new Customer("Eric").openAccount(anyAccount2);
		Account from = anyAccount, to = anyAccount2;
		Account.customerTransfer(from, to, 10.0);
	}

	@Test(expected=IllegalArgumentException.class)
	public void givenTwoAccountsOfSameCustomer_whenTransferNegativeAmount_thenRaiseException() {
		Customer customer = new Customer("John");
		customer.openAccount(from);
		customer.openAccount(to);
		Account.customerTransfer(from, to, -10.0);
	}
	
	@Test
	public void givenTwoAccountsOfSameCustomer_whenTransfer_assumingIdeallyWithoutHarssleLikeAtomicEtc_thenAddTwoTransactions() {
		Customer customer = new Customer("John");
		customer.openAccount(from);
		customer.openAccount(to);
		Account.customerTransfer(from, to, 10.0);
		
		assertAmount(-10.0, from.getTransactions().get(0).getAmount());
		assertAmount(10.0, to.getTransactions().get(0).getAmount());
	}
	
	private void assertAmount(double expect, double actual) {
	    assertEquals(expect, actual, 1e-15);
	}
}
