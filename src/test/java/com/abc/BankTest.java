package com.abc;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import com.abc.account.Account;
import com.abc.account.CheckingAccount;
import com.abc.account.MaxiSavingsAccount;
import com.abc.account.SavingsAccount;

public class BankTest {

	private static final double DOUBLE_DELTA = 1e-15;

	private Bank bank;

	@Before
	public void setUp() throws Exception {
		bank = new Bank();
	}

	@Test
	public void testCustomerSummary() {

		Customer john = new Customer("John");
		john.openAccount(new CheckingAccount());
		bank.addCustomer(john);

		assertEquals("Customer Summary\n - John (1 account)",
				bank.customerSummary());
	}

	@Test
	public void testCheckingAccountInterestPaid() {

		Account checkingAccount = new CheckingAccount();
		Customer bill = new Customer("Bill").openAccount(checkingAccount);
		bank.addCustomer(bill);

		checkingAccount.deposit(100.0);

		assertEquals(0.1, bank.totalInterestPaid(), DOUBLE_DELTA);
	}

	@Test
	public void testSavingsAccountInterestPaid() {

		Account savingsAccount = new SavingsAccount();
		bank.addCustomer(new Customer("Bill").openAccount(savingsAccount));

		savingsAccount.deposit(1500.0);

		assertEquals(2.0, bank.totalInterestPaid(), DOUBLE_DELTA);
	}

	@Test
	public void testMaxiSavingsAccountInterestPaid() {

		Account maxiSavingsAccount = new MaxiSavingsAccount();
		bank.addCustomer(new Customer("Bill").openAccount(maxiSavingsAccount));

		maxiSavingsAccount.deposit(3000.0);

		assertEquals(3000 * .05, bank.totalInterestPaid(), DOUBLE_DELTA);
	}

}
