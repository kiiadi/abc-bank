package com.abc;

import junit.framework.TestCase;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CustomerTest {
	

	@Test
	// Test customer statement generation
	public void testApp() {
		Account checkingAccount = new Account(Account.CHECKING);
		Account savingsAccount = new Account(Account.SAVINGS);

		Customer henry = new Customer("Henry").openAccount(checkingAccount)
				.openAccount(savingsAccount);

		henry.accountDeposit(checkingAccount, 100.0);
		henry.accountDeposit(savingsAccount, 4000.0);
		henry.accountwithdraw(savingsAccount, 200.0);
		assertEquals("Statement for " + henry.getName() + "\n" + "\n"
				+ "Checking Account \n" + " Deposit 100.0\n"
				+ "Total 100.0\n\n\n" + "Savings Account \n"
				+ " Deposit 4000.0\n" + " Withdraw 200.0\n" + "Total 3800.0\n"
				+ "\n" + "Total In All Accounts $3,900.00",
				henry.getStatement());
	}

	@Test
	public void testTransferFunds() {
		Account checkingAccount = new Account(Account.CHECKING);
		Account savingsAccount = new Account(Account.SAVINGS);

		Customer henry = new Customer("Henry").openAccount(checkingAccount)
				.openAccount(savingsAccount);

		henry.accountDeposit(checkingAccount, 100.0);
		henry.accountDeposit(savingsAccount, 4000.0);
		henry.accountwithdraw(savingsAccount, 200.0);
		assertEquals("Statement for " + henry.getName() + "\n" + "\n"
				+ "Checking Account \n" + " Deposit 100.0\n"
				+ "Total 100.0\n\n\n" + "Savings Account \n"
				+ " Deposit 4000.0\n" + " Withdraw 200.0\n"+"Total 3800.0\n" + "\n"
				+ "Total In All Accounts $3,900.00", henry.getStatement());
		// Transfer the money from savings to checking
		henry.transferMoney(savingsAccount, checkingAccount, 200.0);
		assertEquals("Statement for " + henry.getName() + "\n" + "\n"
				+ "Checking Account \n" + " Deposit 100.0\n"
				+ " Deposit 200.0\n" + "Total 300.0\n\n\n"
				+ "Savings Account \n" + " Deposit 4000.0\n"
				+ " Withdraw 200.0\n" +  " Withdraw 200.0\n" + "Total 3600.0\n" + "\n"
				+ "Total In All Accounts $3,900.00", henry.getStatement());
		// Transfer amount that is not in the savings account. Result no change
		// in the savings account
		henry.transferMoney(savingsAccount, checkingAccount, 4100.0);
		assertEquals("Statement for " + henry.getName() + "\n" + "\n"
				+ "Checking Account \n" + " Deposit 100.0\n"
				+ " Deposit 200.0\n" + "Total 300.0\n\n\n"
				+ "Savings Account \n" + " Deposit 4000.0\n"
				+ " Withdraw 200.0\n" + " Withdraw 200.0\n" + "Total 3600.0\n" + "\n"
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
}
