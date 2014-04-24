package com.abc;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Test;

import com.abc.account.Account;
import com.abc.account.CheckingAccount;
import com.abc.account.MaxiSavingsAccount;
import com.abc.account.SavingsAccount;

public class BankTest {

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

		checkingAccount.deposit(new BigDecimal("100.0"));

		assertEquals(new BigDecimal("0.10"), bank.totalInterestPaid());
	}

	@Test
	public void testSavingsAccountInterestPaid() {

		Account savingsAccount = new SavingsAccount();
		bank.addCustomer(new Customer("Bill").openAccount(savingsAccount));

		savingsAccount.deposit(new BigDecimal("1500.00"));

		assertEquals(new BigDecimal("2.00"), bank.totalInterestPaid());
	}

	@Test
	public void testMaxiSavingsAccountInterestPaid() {

		Account maxiSavingsAccount = new MaxiSavingsAccount();
		bank.addCustomer(new Customer("Bill").openAccount(maxiSavingsAccount));

		maxiSavingsAccount.deposit(new BigDecimal("3000.00"));

		BigDecimal expected = new BigDecimal("3000.00").multiply(
				new BigDecimal(".05")).setScale(2, BigDecimal.ROUND_CEILING);
		assertEquals(expected, bank.totalInterestPaid());
	}

}
