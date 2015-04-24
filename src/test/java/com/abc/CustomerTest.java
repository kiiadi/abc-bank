package com.abc;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.abc.account.Account;
import com.abc.account.AccountFactory;
import com.abc.account.AccountType;

public class CustomerTest {

	@Test
	// Test customer statement generation
	public void testApp() {
		CustomerBuilder customerBuilder = new CustomerBuilder();
		AccountFactory accountFactory = new AccountFactory();
		Account checkingAccount = accountFactory.create(AccountType.CHECKING);
		Account savingsAccount = accountFactory.create(AccountType.SAVINGS);

		Customer henry = customerBuilder.build("Henry")
				.openAccount(checkingAccount).openAccount(savingsAccount);

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
		CustomerBuilder customerBuilder = new CustomerBuilder();
		AccountFactory accountFactory = new AccountFactory();

		Customer oscar = customerBuilder.build("Oscar").openAccount(
				accountFactory.create(AccountType.SAVINGS));
		assertEquals(1, oscar.getNumberOfAccounts());
	}

	@Test
	public void testTwoAccount() {
		CustomerBuilder customerBuilder = new CustomerBuilder();
		AccountFactory accountFactory = new AccountFactory();

		Customer oscar = customerBuilder.build("Oscar").openAccount(
				accountFactory.create(AccountType.SAVINGS));
		oscar.openAccount(accountFactory.create(AccountType.CHECKING));
		assertEquals(2, oscar.getNumberOfAccounts());
	}

	@Test
	public void testThreeAcounts() {
		CustomerBuilder customerBuilder = new CustomerBuilder();
		AccountFactory accountFactory = new AccountFactory();
		Customer oscar = customerBuilder.build("Oscar").openAccount(
				accountFactory.create(AccountType.SAVINGS));
		oscar.openAccount(accountFactory.create(AccountType.CHECKING));
		oscar.openAccount(accountFactory.create(AccountType.MAXI_SAVINGS));
		assertEquals(3, oscar.getNumberOfAccounts());
	}
}
