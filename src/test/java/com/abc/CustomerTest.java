package com.abc;

import org.junit.Test;

import com.abc.Account.AccountType;

import static org.junit.Assert.assertEquals;

public class CustomerTest {

	private static final double DOUBLE_DELTA = 1e-15;

	/**
	 * Test customer statement generation
	 */
	@Test
	public void testApp() {

		Account checkingAccount = new Account(AccountType.CHECKING);
		Account savingsAccount = new Account(AccountType.SAVINGS);

		Customer henry = new Customer("Henry").openAccount(checkingAccount)
				.openAccount(savingsAccount);

		checkingAccount.deposit(100.0);
		savingsAccount.deposit(4000.0);
		savingsAccount.withdraw(200.0);

		assertEquals("Statement for Henry\n" + "\n" + "Checking Account\n"
				+ "  deposit $100.00\n" + "Total $100.00\n" + "\n"
				+ "Savings Account\n" + "  deposit $4,000.00\n"
				+ "  withdrawal $200.00\n" + "Total $3,800.00\n" + "\n"
				+ "Total In All Accounts $3,900.00", henry.getStatement());
	}

	/**
	 * Test opening of 1 account
	 */
	@Test
	public void testOneAccount() {
		Customer oscar = new Customer("Oscar").openAccount(new Account(
				AccountType.SAVINGS));
		assertEquals(1, oscar.getNumberOfAccounts());
	}

	/**
	 * Test opening of 2 accounts
	 */
	@Test
	public void testTwoAccount() {
		Customer oscar = new Customer("Oscar").openAccount(new Account(
				AccountType.SAVINGS));
		oscar.openAccount(new Account(AccountType.CHECKING));
		assertEquals(2, oscar.getNumberOfAccounts());
	}

	/**
	 * Test opening of 3 accounts
	 */
	@Test
	public void testThreeAcounts() {
		Customer oscar = new Customer("Oscar").openAccount(new Account(
				AccountType.SAVINGS));
		oscar.openAccount(new Account(AccountType.CHECKING));
		oscar.openAccount(new Account(AccountType.MAXI_SAVINGS));
		assertEquals(3, oscar.getNumberOfAccounts());
	}

	/**
	 * Test fund transfer between different account types of a customer
	 * Throws exception to indicate transfer can not be between same account types
	 * This assumption is due to no account number in Account class
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testTransferFundSameAccounts() {
		Account fromAccount = new Account(AccountType.CHECKING);
		Account toAccount = new Account(AccountType.CHECKING);
		Customer oscar = new Customer("Oscar").openAccount(fromAccount);
		oscar.openAccount(toAccount);
		oscar.transferFunds(fromAccount, toAccount, 100);

	}

	/**
	 * Test fund transfer between different account types of a customer
	 */
	@Test
	public void testTransferFundDifferentAccounts() {
		Account fromAccount = new Account(AccountType.CHECKING);
		Account toAccount = new Account(AccountType.SAVINGS);
		Customer oscar = new Customer("Oscar").openAccount(fromAccount);
		oscar.openAccount(toAccount);
		fromAccount.deposit(2000.0);
		toAccount.deposit(3000.0);
		oscar.transferFunds(fromAccount, toAccount, 1000.0);
		assertEquals(4000.0, toAccount.sumTransactions(), DOUBLE_DELTA);

	}
}
