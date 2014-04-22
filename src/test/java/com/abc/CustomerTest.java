package com.abc;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.abc.account.Account;
import com.abc.account.CheckingAccount;
import com.abc.account.SavingsAccount;

public class CustomerTest {
	// TODO get rid of this
	private static final double DOUBLE_DELTA = 1e-15;

	@Test
	public void testGetStatement() throws Exception {

		Account checkingAccount = new CheckingAccount();
		Account savingsAccount = new SavingsAccount();

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

	// TODO loop

	@Test
	public void testOneAccount() {
		Customer oscar = new Customer("Oscar")
				.openAccount(new SavingsAccount());
		assertEquals(1, oscar.getNumberOfAccounts());
	}

	@Test
	public void testTwoAccount() {
		Customer oscar = new Customer("Oscar")
				.openAccount(new SavingsAccount());
		oscar.openAccount(new CheckingAccount());
		assertEquals(2, oscar.getNumberOfAccounts());
	}

	// TODO fix this test

	@Test
	public void testTransfer() throws Exception {

		Account checkingAccount = new CheckingAccount();
		Account savingsAccount = new SavingsAccount();

		Customer henry = new Customer("Henry").openAccount(checkingAccount)
				.openAccount(savingsAccount);

		checkingAccount.deposit(100.0);
		savingsAccount.deposit(4000.0);

		henry.transfer(savingsAccount, checkingAccount, 4000.00);

		assertEquals(4104.10, checkingAccount.balance(), DOUBLE_DELTA); // accounts
																		// for
																		// interest
																		// earned
		assertEquals(savingsAccount.balance(), 0, DOUBLE_DELTA);

	}

	@Test(expected = Exception.class)
	public void testTransferInsufficientBalance() throws Exception {

		Account checkingAccount = new CheckingAccount();
		Account savingsAccount = new SavingsAccount();

		Customer henry = new Customer("Henry").openAccount(checkingAccount)
				.openAccount(savingsAccount);

		checkingAccount.deposit(100.0);
		savingsAccount.deposit(4000.0);

		henry.transfer(savingsAccount, checkingAccount, 5000.00);

	}

	@Test(expected = IllegalArgumentException.class)
	public void testTransferIllegalArgumentException() throws Exception {

		Account checkingAccount = new CheckingAccount();
		Account savingsAccount = new SavingsAccount();

		Customer henry = new Customer("Henry").openAccount(checkingAccount)
				.openAccount(savingsAccount);

		checkingAccount.deposit(100.0);
		savingsAccount.deposit(4000.0);

		henry.transfer(savingsAccount, checkingAccount, 0);

	}

}
