package com.abc;

import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.assertEquals;

public class CustomerTest {
	private static final double DOUBLE_DELTA = 1e-10;

	@Test
	public void testInvalidTransfer() {
		Account checkingAccount = new Account(AccountType.CHECKING);
		Account savingsAccount = new Account(AccountType.SAVINGS);
		Customer henry = new Customer("Henry").openAccount(checkingAccount).openAccount(savingsAccount);
		checkingAccount.deposit(1000);
		savingsAccount.deposit(1000);

		assertEquals(1000, checkingAccount.getBalance(), DOUBLE_DELTA);
		assertEquals(1000, savingsAccount.getBalance(), DOUBLE_DELTA);

		henry.performTransfer(1001, checkingAccount, savingsAccount);

		// the previous transfer should not have worked and balances should stay the same
		assertEquals(1000, checkingAccount.getBalance(), DOUBLE_DELTA);
		assertEquals(1000, savingsAccount.getBalance(), DOUBLE_DELTA);
	}

	@Test
	public void testPerformTransfer() {
		Account checkingAccount = new Account(AccountType.CHECKING);
		Account savingsAccount = new Account(AccountType.SAVINGS);
		Date today = new Date();
		Customer henry = new Customer("Henry").openAccount(checkingAccount).openAccount(savingsAccount);
		checkingAccount.deposit(1000);
		savingsAccount.deposit(1000);

		assertEquals(1000, checkingAccount.getBalance(), DOUBLE_DELTA);
		assertEquals(1000, savingsAccount.getBalance(), DOUBLE_DELTA);

		henry.performTransfer(500, checkingAccount, savingsAccount);

		assertEquals(500, checkingAccount.getBalance(), DOUBLE_DELTA);
		assertEquals(1500, savingsAccount.getBalance(), DOUBLE_DELTA);
	}

	@Test
	public void testGenerateCustomerStatement() {

		Account checkingAccount = new Account(AccountType.CHECKING);
		Account savingsAccount = new Account(AccountType.SAVINGS);

		Customer henry = new Customer("Henry").openAccount(checkingAccount).openAccount(savingsAccount);

		checkingAccount.deposit(100.0);
		savingsAccount.deposit(4000.0);
		try {
			savingsAccount.withdraw(200.0);
		} catch (AccountModificationException e) {
			e.printStackTrace();
		}

		assertEquals("Statement for Henry\n" +
				"\n" +
				"Checking Account\n" +
				"  deposit $100.00\n" +
				"Total $100.00\n" +
				"\n" +
				"Savings Account\n" +
				"  deposit $4,000.00\n" +
				"  withdrawal $200.00\n" +
				"Total $3,800.00\n" +
				"\n" +
				"Total In All Accounts $3,900.00", henry.getStatement());
	}

	@Test
	public void testOneAccount() {
		Customer oscar = new Customer("Oscar").openAccount(new Account(AccountType.SAVINGS));
		assertEquals(1, oscar.getNumberOfAccounts());
	}

	@Test
	public void testTwoAccount() {
		Customer oscar = new Customer("Oscar")
				.openAccount(new Account(AccountType.SAVINGS));
		oscar.openAccount(new Account(AccountType.CHECKING));
		assertEquals(2, oscar.getNumberOfAccounts());
	}

	@Test
	public void testThreeAccounts() {
		Customer oscar = new Customer("Oscar")
				.openAccount(new Account(AccountType.SAVINGS));
		oscar.openAccount(new Account(AccountType.CHECKING));
		oscar.openAccount(new Account(AccountType.MAXI_SAVINGS));
		assertEquals(3, oscar.getNumberOfAccounts());
	}
}
