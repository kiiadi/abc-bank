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
	public void testValidTransfer() {
		Account checkingAccount = new Account(AccountType.CHECKING);
		Account savingsAccount = new Account(AccountType.SAVINGS);
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
		Date today = DateUtil.getInstance().now();
		Date tenDaysAgo = DateUtil.getInstance().addDays(today, -10);
		Date fiveDaysAgo = DateUtil.getInstance().addDays(today, -5);

		Account checkingAccount = new Account(AccountType.CHECKING);
		Account savingsAccount = new Account(AccountType.SAVINGS);
		Account maxiSavingsAccount = new Account(AccountType.MAXI_SAVINGS);

		Customer henry = new Customer("Henry");
		henry.openAccount(checkingAccount);
		henry.openAccount(savingsAccount);
		henry.openAccount(maxiSavingsAccount);

		checkingAccount.deposit(1000.0, tenDaysAgo);
		savingsAccount.deposit(4000.0, tenDaysAgo);
		maxiSavingsAccount.deposit(5000.0, fiveDaysAgo);

		try {
			savingsAccount.withdraw(200.0);
		} catch (AccountModificationException e) {
			e.printStackTrace();
		}

		assertEquals("Statement for Henry\n" +
				"\n" +
				"Checking Account\n" +
				"  deposit $1,000.00\n" +
				"Accrued Interest $0.03\n" +
				"Total $1,000.03\n" +
				"\n" +
				"Savings Account\n" +
				"  deposit $4,000.00\n" +
				"  withdrawal $200.00\n" +
				"Accrued Interest $0.19\n" +
				"Total $3,800.19\n" +
				"\n" +
				"Maxi Savings Account\n" +
				"  deposit $5,000.00\n" +
				"Accrued Interest $3.43\n" +
				"Total $5,003.43\n" +
				"\n" +
				"Total In All Accounts $9,803.64", henry.generateStatement());
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
