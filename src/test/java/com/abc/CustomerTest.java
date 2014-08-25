package com.abc;

import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.*;

public class CustomerTest {

	private static final double DOUBLE_DELTA = 1e-15;

	@Rule
	public ExpectedException exception = ExpectedException.none();

	@Test
	// Test customer statement generation
	public void testGetStatement() {

		Account checkingAccount = new Account(Account.CHECKING);
		Account savingsAccount = new Account(Account.SAVINGS);

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

	@Test
	public void testOneAccount() {
		Customer oscar = new Customer("Oscar").openAccount(new Account(
				Account.SAVINGS));
		assertEquals(1, oscar.getNumberOfAccounts());
	}

	@Test
	public void testTwoAccount() {
		Customer oscar = new Customer("Oscar").openAccount(new Account(
				Account.SAVINGS));
		oscar.openAccount(new Account(Account.CHECKING));
		assertEquals(2, oscar.getNumberOfAccounts());
	}

	@Ignore
	public void testThreeAcounts() {
		Customer oscar = new Customer("Oscar").openAccount(new Account(
				Account.SAVINGS));
		oscar.openAccount(new Account(Account.CHECKING));
		assertEquals(3, oscar.getNumberOfAccounts());
	}

	@Test
	public void testTotalInterestEarned() {

		Account checkingAccount = new Account(Account.CHECKING);
		Customer bill = new Customer("Bill");
		bill.openAccount(checkingAccount);
		checkingAccount.deposit(100.0);

		Account savingsAccount = new Account(Account.SAVINGS);
		bill.openAccount(savingsAccount);
		savingsAccount.deposit(1500.0);

		assertEquals(2.1, bill.totalInterestEarned(), DOUBLE_DELTA);

	}

	@Test
	public void testTransferBetweenValidAccountsWithValidAmount() {

		Account checkingAccount = new Account(Account.CHECKING);
		Customer bill = new Customer("Bill");
		bill.openAccount(checkingAccount);
		checkingAccount.deposit(1500.0);

		Account savingsAccount = new Account(Account.SAVINGS);
		bill.openAccount(savingsAccount);

		assertTrue(bill.transferBetweenAccounts(Account.CHECKING,
				Account.SAVINGS, 500));
		assertEquals(1000, checkingAccount.getBalance(), DOUBLE_DELTA);
		assertEquals(500, savingsAccount.getBalance(), DOUBLE_DELTA);

	}

	
	// Tests Failure scenario.
	@Test
	public void testTransferBetweenValidAccountsWithInValidAmount() {

		Account checkingAccount = new Account(Account.CHECKING);
		Customer bill = new Customer("Bill");
		bill.openAccount(checkingAccount);
		checkingAccount.deposit(1500.0);

		Account savingsAccount = new Account(Account.SAVINGS);
		bill.openAccount(savingsAccount);

		exception.expect(IllegalArgumentException.class);
		bill.transferBetweenAccounts(Account.CHECKING, Account.SAVINGS, 2000);

	}
	
	// Tests Failure scenario.
	@Test
	public void testTransferBetweenInValidAccounts() {

		Account checkingAccount = new Account(Account.CHECKING);
		Customer bill = new Customer("Bill");
		bill.openAccount(checkingAccount);
		checkingAccount.deposit(1500.0);

		Account savingsAccount = new Account(Account.SAVINGS);
		bill.openAccount(savingsAccount);

		exception.expect(IllegalArgumentException.class);
		bill.transferBetweenAccounts(Account.CHECKING, Account.MAXI_SAVINGS, 2000);

	}

}
