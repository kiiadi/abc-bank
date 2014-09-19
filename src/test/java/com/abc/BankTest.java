package com.abc;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class BankTest {
	private Bank bank;

	Customer tesla;
	Customer charlie;
	Customer rob;

	public BankTest() {
	}

	@Before
	public void setUp() throws Exception {
		bank = new Bank();
		tesla = new Customer("tesla");
		charlie = new Customer("charlie");
		rob = new Customer("rob");

		bank.addCustomer(rob);
		bank.addCustomer(charlie);
		bank.addCustomer(tesla);

		Account teslaAccount = new Account(AccountType.CHECKING);
		Account charlieAccount = new Account(AccountType.CHECKING);
		Account robAccount = new Account(AccountType.CHECKING);

		rob.openAccount(robAccount);
		charlie.openAccount(charlieAccount);
		tesla.openAccount(teslaAccount);

		teslaAccount.deposit(1000.0d);
		charlieAccount.deposit(2000.0d);
		charlieAccount.transfer(1000.0d, robAccount);

	}

	@Test
	public void testGetCustomers() {
		assertEquals(3, bank.getCustomers().size());
	}

	@Test
	public void testAddCustomer() {
		Customer robie = new Customer("robie");
		assertEquals(3, bank.getCustomers().size());
		bank.addCustomer(robie);
		assertEquals(4, bank.getCustomers().size());
	}

	@Test
	public void testGetFirstCustomer() {
		assertEquals("rob", bank.getCustomers().get(0).getName());
	}

	@Test
	public void testCustomerSummary() {
		String expectedResult = "Customer Summary" + "\n"
				+ " - rob (1 account)" + "\n" + " - charlie (1 account)" + "\n"
				+ " - tesla (1 account)";
		String actualResult = bank.customerSummary();
		assertEquals(expectedResult, actualResult);
	}

	@Test
	public void testTotalInterestPaid() {
		assertEquals(3, bank.getCustomers().size());

		Double expectedValue = 3.0d;
		Double actualValue = bank.totalInterestPaid();
		assertEquals(expectedValue, actualValue);
	}

}
