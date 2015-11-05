package com.abc;

import org.junit.Ignore;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CustomerTest {

	@Test // Test customer statement generation
	public void testApp() {

		// create three different kinds of accounts
		Account checkingAccount = new Account(Account.CHECKING);
		Account savingsAccount = new Account(Account.SAVINGS);
		Account maxiSavingAccount = new Account(Account.MAXI_SAVINGS);

		// create a customer called "Henry", he opens all these accounts.
		Customer henry = new Customer("Henry").openAccount(checkingAccount).openAccount(savingsAccount)
				.openAccount(maxiSavingAccount);

		// deposit and withdraw in his checking account
		checkingAccount.deposit(100.0);
		checkingAccount.withdraw(50.0);

		// deposit and withdraw in his saving account
		savingsAccount.deposit(4000.0);
		savingsAccount.withdraw(200.0);

		// deposit and withdraw in his maxiSaving account
		maxiSavingAccount.deposit(3000.0);
		maxiSavingAccount.withdraw(1500.0);

		assertEquals("Statement for Henry\n" + "\n" + "Checking Account\n" + "  deposit $100.00\n"
				+ "  withdrawal -$50.00\n" + "Total $50.00\n" + "\n" + "Savings Account\n" + "  deposit $4,000.00\n"
				+ "  withdrawal -$200.00\n" + "Total $3,800.00\n" + "\n" + "Maxi Savings Account\n"
				+ "  deposit $3,000.00\n" + "  withdrawal -$1,500.00\n" + "Total $1,500.00\n" + "\n"
				+ "Total In All Accounts $5,350.00", henry.getStatement());
	}

	// Test customer statement generation with negative balance
	@Test
	public void testApp2() {

		// create three different kinds of accounts
		Account checkingAccount = new Account(Account.CHECKING);
		Account savingsAccount = new Account(Account.SAVINGS);
		Account maxiSavingAccount = new Account(Account.MAXI_SAVINGS);

		// create a customer called "henry", he opens all these accounts.
		Customer henry = new Customer("Henry").openAccount(checkingAccount).openAccount(savingsAccount)
				.openAccount(maxiSavingAccount);

		// deposit and withdraw in his checking account
		checkingAccount.deposit(100.0);
		checkingAccount.withdraw(150.0);

		// deposit and withdraw in his saving account
		savingsAccount.deposit(4000.0);
		savingsAccount.withdraw(4200.0);

		// deposit and withdraw in his maxiSaving account
		maxiSavingAccount.deposit(3000.0);
		maxiSavingAccount.withdraw(3500.0);

		assertEquals("Statement for Henry\n" + "\n" + "Checking Account\n" + "  deposit $100.00\n"
				+ "  withdrawal -$150.00\n" + "Total -$50.00\n" + "\n" + "Savings Account\n" + "  deposit $4,000.00\n"
				+ "  withdrawal -$4,200.00\n" + "Total -$200.00\n" + "\n" + "Maxi Savings Account\n"
				+ "  deposit $3,000.00\n" + "  withdrawal -$3,500.00\n" + "Total -$500.00\n" + "\n"
				+ "Total In All Accounts -$750.00", henry.getStatement());
	}

	@Test
	public void testOneAccount() {
		// Customer Oscar opens a saving account
		Customer oscar = new Customer("Oscar").openAccount(new Account(Account.SAVINGS));

		assertEquals(1, oscar.getNumberOfAccounts());
	}

	@Test
	public void testTwoAccount() {
		// Customer Oscar opens a saving account and a checking account
		Customer oscar = new Customer("Oscar").openAccount(new Account(Account.SAVINGS));
		oscar.openAccount(new Account(Account.CHECKING));

		assertEquals(2, oscar.getNumberOfAccounts());
	}

	@Test
	public void testThreeAcounts() {
		// Customer Oscar opens a saving account and a checking account and a
		// maxiSaving account
		Customer oscar = new Customer("Oscar").openAccount(new Account(Account.SAVINGS));
		oscar.openAccount(new Account(Account.CHECKING));
		oscar.openAccount(new Account(Account.MAXI_SAVINGS));

		assertEquals(3, oscar.getNumberOfAccounts());
	}

	// Transfer test
	@Test
	public void testTransfer() {
		// Create two kinds of accounts, which are checking account and saving
		// account
		Account checkingAccount = new Account(Account.CHECKING);
		Account savingAccount = new Account(Account.SAVINGS);

		// Customer Bill opens a checking account and deposit $1000 on it
		Customer bill = new Customer("Bill");
		bill.openAccount(checkingAccount);
		checkingAccount.deposit(1000.0);

		// Customer Bill opens a saving account and deposit $1000 on it
		bill.openAccount(savingAccount);
		savingAccount.deposit(1000.0);

		/*
		 * Bill transfer $500 from his checking to his saving account, which
		 * means he withdraws $500 from his checking account and deposit $500
		 * into his saving account
		 */

		bill.transfer(checkingAccount, savingAccount, 500.0);

		assertEquals("Statement for Bill\n" + "\n" + "Checking Account\n" + "  deposit $1,000.00\n"
				+ "  withdrawal -$500.00\n" + "Total $500.00\n" + "\n" + "Savings Account\n" + "  deposit $1,000.00\n"
				+ "  deposit $500.00\n" + "Total $1,500.00\n" + "\n" + "Total In All Accounts $2,000.00",
				bill.getStatement());
	}

}
