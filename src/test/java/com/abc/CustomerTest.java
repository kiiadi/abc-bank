package com.abc;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotSame;

import org.junit.Before;
import org.junit.Test;

public class CustomerTest {

	private Customer jammie;

	public CustomerTest() {
	}

	@Before
	public void setUp() throws Exception {

		jammie = new Customer("Joe").openAccount(new Account(
				AccountType.SAVINGS));

		jammie.openAccount(new Account(AccountType.MAXI_SAVINGS));

	}

	@Test
	public void testGetNumberOfAccountsZero() {
		Customer oscar = new Customer("Oscar");
		assertEquals(0, oscar.getNumberOfAccounts());
	}

	@Test
	public void testGetNumberOfAccountsOne() {
		Customer oscar = new Customer("Oscar").openAccount(new Account(
				AccountType.SAVINGS));
		assertEquals(1, oscar.getNumberOfAccounts());
	}

	@Test
	public void testGetNumberOfAccountsTwo() {
		Customer oscar = new Customer("Oscar").openAccount(new Account(
				AccountType.SAVINGS));
		oscar.openAccount(new Account(AccountType.CHECKING));
		assertEquals(2, oscar.getNumberOfAccounts());
	}

	@Test
	public void testGetNumberOfAccountsThree() {
		Customer oscar = new Customer("Oscar").openAccount(new Account(
				AccountType.SAVINGS));
		oscar.openAccount(new Account(AccountType.CHECKING));
		assertNotSame(3, oscar.getNumberOfAccounts());
	}

	@Test
	public void testGetAccounts() {
		int size = jammie.getAccounts().size();
		assertEquals(2, size);
	}

	@Test
	public void testOpenAccount() {
		Customer oscar = new Customer("Oscar").openAccount(new Account(
				AccountType.SAVINGS));
		oscar.openAccount(new Account(AccountType.CHECKING));
		int size = oscar.getAccounts().size();
		assertEquals(2, size);

	}

	@Test
	public void testTotalInterestEarned() {

		Double expected = 75.0d;
		jammie.openAccount(new Account(AccountType.CHECKING));

		Double amount = 2000.0d;
		for (Account account : jammie.getAccounts()) {
			account.deposit(amount);
		}

		Double earnedInt = jammie.totalInterestEarned();

		assertEquals(expected, earnedInt);
	}

	@Test
	public void testGetSummary() {
		String expectedResult = "\n" + " - Oscar (No accounts)";
		Customer oscar = new Customer("Oscar");
		Account oscarAccount = new Account(AccountType.CHECKING);
		String actualResult = oscar.getSummary();

		assertEquals(expectedResult, actualResult);

		expectedResult = "\n" + " - Oscar (1 account)";
		oscar.openAccount(oscarAccount);
		actualResult = oscar.getSummary();

		assertEquals(expectedResult, actualResult);

		expectedResult = "\n" + " - Oscar (2 accounts)";
		oscar.openAccount(oscarAccount);
		actualResult = oscar.getSummary();

		assertEquals(expectedResult, actualResult);

	}

	@Test
	public void testGetStatement() {
		String expectedResult = "Statement for Oscar" + "\n\n"
				+ "Total In All Accounts $0.00";
		Customer oscar = new Customer("Oscar");
		Account oscarAccount = new Account(AccountType.CHECKING);
		String actualResult = oscar.getStatement();

		assertEquals(expectedResult, actualResult);

		expectedResult = "Statement for Oscar" + "\n\n"
				+ "Total In All Accounts $0.00";
		oscarAccount.deposit(1000.0d);
		actualResult = oscar.getStatement();

		assertEquals(expectedResult, actualResult);

		expectedResult = "Statement for Oscar" + "\n\n" + "Checking Account"
				+ "\n" + "  0: deposit $1,000.00" + "\n"
				+ "  0: deposit $1,000.00" + "\n" + "Total $2,000.00" + "\n\n"
				+ "Total In All Accounts $2,000.00";
		oscar.openAccount(oscarAccount);
		oscarAccount.deposit(1000.0d);
		actualResult = oscar.getStatement();

		assertEquals(expectedResult, actualResult);

	}

	@Test
	public void testStatementForAccount() {
		String expectedResult = "Checking Account" + "\n" + "Total $0.00";
		Account oscarAccount = new Account(AccountType.CHECKING);
		String actualResult = new CustomerStatementBuilder()
				.statementForAccount(oscarAccount);

		assertEquals(expectedResult, actualResult);

		expectedResult = "Checking Account" + "\n" + "  0: deposit $1,000.00"
				+ "\n" + "Total $1,000.00";
		oscarAccount = new Account(AccountType.CHECKING);
		oscarAccount.deposit(1000.0d);
		actualResult = new CustomerStatementBuilder()
				.statementForAccount(oscarAccount);

		assertEquals(expectedResult, actualResult);

		oscarAccount.withdraw(500.0d);
		actualResult = new CustomerStatementBuilder()
				.statementForAccount(oscarAccount);
		expectedResult = "Checking Account" + "\n" + "  0: deposit $1,000.00"
				+ "\n" + "  1: withdrawal $500.00" + "\n" + "Total $500.00";
		assertEquals(expectedResult, actualResult);

		try {
			oscarAccount.withdraw(1500.0d);
		} catch (Exception e) {
			actualResult = e.getMessage();
		}
		actualResult = new CustomerStatementBuilder()
				.statementForAccount(oscarAccount);
		expectedResult = "Checking Account" + "\n" + "  0: deposit $1,000.00"
				+ "\n" + "  1: withdrawal $500.00" + "\n" + "Total $500.00";
		assertEquals(expectedResult, actualResult);

	}

}
