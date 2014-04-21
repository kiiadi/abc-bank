package com.abc.bank;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.abc.accounts.Account;
import com.abc.accounts.AccountFactory;
import com.abc.accounts.AccountType;
import com.abc.customer.Customer;
import com.abc.exceptions.DuplicateAccountException;
import com.abc.exceptions.DuplicateCustomerException;
import com.abc.exceptions.InvalidTransactionAmountException;

/**
 * Test-cases follow the following method-name pattern:
 * methodName_Scenario_ExpectedResult()
 *
 */
public class BankTest {
	private Bank bank;
	
	@Before
	public void setUp() throws Exception {
		bank = new Bank();
	}

	@Test
	public void getCustomers_ForNewBankInstance_CustomersListIsInitlialized() {
		assertNotNull(bank.getCustomers());
		assertEquals(bank.getCustomers().size(), 0);
	}
	
	@Test
	public void addCustomer_WhenCalled_CustomerIsAdded() throws DuplicateCustomerException {
		Customer john = new Customer(1, "John");
		bank.addCustomer(john);
		
		assertEquals(bank.getCustomers().size(), 1);
	}

	@Test(expected=DuplicateCustomerException.class)
	public void addCustomer_WhenDuplicateCustomerIsAdded_DuplicateCustomerExceptionIsThrown()
			throws DuplicateCustomerException {
		bank.getCustomers().clear();
		
		Customer john = new Customer(1, "John");
		bank.addCustomer(john);
		
		Customer henry = new Customer(1, "Henry");
		bank.addCustomer(henry);
		
		// add duplicate customer to the list
		bank.addCustomer(john);
	}
	
	@Test
	public void totalInterestPaid_WhenCalled_TotalInterestPaidIsReturned()
			throws DuplicateCustomerException, DuplicateAccountException, InvalidTransactionAmountException {
		bank.getCustomers().clear();
		Customer john = new Customer(1, "John");
		
		Account checking = AccountFactory.createAccount(AccountType.CHECKING_ACCOUNT);
		checking.deposit(1000);
		
		Account savings = AccountFactory.createAccount(AccountType.SAVINGS_ACCOUNT);
		savings.deposit(2000);
		
		john.addAccount(checking).addAccount(savings);
		bank.addCustomer(john);
		
		assertEquals(bank.totalInterestPaid(), 4, 0);
	}
	
	@Test
	public void getFirstCustomer_WhenCalled_FirstCustomerFromTheListIsReturned()
			throws DuplicateCustomerException {
		bank.getCustomers().clear();
		
		Customer john = new Customer(1, "John");
		bank.addCustomer(john);
		
		Customer henry = new Customer(1, "Henry");
		bank.addCustomer(henry);
		
		assertEquals(bank.getFirstCustomer(), "John");
	}
}
