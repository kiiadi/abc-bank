package com.abc;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.junit.Test;

import com.abc.Account.AccountType;

import static org.junit.Assert.assertEquals;

public class BankTest {
	private static final double DOUBLE_DELTA = 1e-15;
    
	/**
	 * Test customer summary
	 */
	@Test
	public void testCustomerSummary() {
		Bank bank = new Bank();
		Customer john = new Customer("John");
		john.openAccount(new Account(AccountType.CHECKING));
		bank.addCustomer(john);

		assertEquals("Customer Summary\n - John (1 account)",
				bank.customerSummary());
	}

	/**
	 * Test interest for Checking account customer
	 * Test would fail as the number of days used in calculation depends on the day of the run
	 * Expected value may be modified to make the test pass
	 */
	@Test
	public void testCheckingAccount() {
		Calendar cal = new GregorianCalendar();
		cal.set(2014, Calendar.NOVEMBER, 5, 5, 30, 25);
		Date depositDate = cal.getTime();
		Bank bank = new Bank();
		Account checkingAccount = new Account(AccountType.CHECKING);
		Customer bill = new Customer("Bill").openAccount(checkingAccount);
		bank.addCustomer(bill);

		checkingAccount.deposit(600.0, depositDate);

		assertEquals(0.01808219178082192, bank.totalInterestPaid(),
				DOUBLE_DELTA);
	}

	/**
	 * Test interest for Savings account customer
	 * Test would fail as the number of days used in calculation depends on the day of the run
	 * Expected value may be modified to make the test pass
	 */
	@Test
	public void testSavingsAccount() {
		Calendar cal = new GregorianCalendar();
		cal.set(2014, Calendar.NOVEMBER, 5, 5, 30, 25);
		Date depositDate = cal.getTime();
		Bank bank = new Bank();
		Account savingsAccount = new Account(AccountType.SAVINGS);
		bank.addCustomer(new Customer("Bill").openAccount(savingsAccount));

		savingsAccount.deposit(1500.0, depositDate);

		assertEquals(0.030136986301369864, bank.totalInterestPaid(),
				DOUBLE_DELTA);
	}

	/**
	 * Test interest for Maxi Savings account customer
	 * Test would fail as the number of days used in calculation depends on the day of the run
	 * Expected value may be modified to make the test pass
	 */
	@Test
	public void testMaxiSavingsAccount() {
		Calendar cal = new GregorianCalendar();
		cal.set(2014, Calendar.NOVEMBER, 5, 5, 30, 25);
		Date depositDate = cal.getTime();
		Bank bank = new Bank();
		Account maxiSavingsAccount = new Account(AccountType.MAXI_SAVINGS);
		bank.addCustomer(new Customer("Bill").openAccount(maxiSavingsAccount));

		maxiSavingsAccount.deposit(3000.0, depositDate);

		assertEquals(4.52054794520548, bank.totalInterestPaid(), DOUBLE_DELTA);
	}
	
	/**
	 * Test the first customer of the Bank
	 */
	@Test
	public void testFirstCustomerName() {
		Bank bank = new Bank();
		Account maxiSavingsAccount = new Account(AccountType.MAXI_SAVINGS);
		Account savingsAccount = new Account(AccountType.SAVINGS);
		bank.addCustomer(new Customer("Bill").openAccount(maxiSavingsAccount));
		bank.addCustomer(new Customer("John").openAccount(savingsAccount));
		assertEquals("Bill", bank.getFirstCustomer());			
	}

}
