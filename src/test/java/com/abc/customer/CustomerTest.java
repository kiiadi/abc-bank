package com.abc.customer;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.abc.accounts.Account;
import com.abc.accounts.AccountFactory;
import com.abc.accounts.AccountType;
import com.abc.exceptions.DuplicateAccountException;
import com.abc.exceptions.InsufficientFundsException;
import com.abc.exceptions.InvalidAccountIdException;
import com.abc.exceptions.InvalidTransactionAmountException;

/**
 * Test-cases follow the following method-name pattern:
 * methodName_Scenario_ExpectedResult()
 *
 */
public class CustomerTest {
	private Customer customer;
	
	@Before
	public void setUp() throws Exception {
		customer = new Customer(1, "John");
	}

	@Test
	public void getCustomerId_ForNewCustomer_CustomerIdIsSet() {
		assertEquals(customer.getCustomerId(), 1);
	}
	
	@Test
	public void getName_ForNewCustomer_NameIsSet() {
		assertTrue(customer.getName().equals("John"));
	}
	
	@Test
	public void getAccounts_ForNewCustomer_AccountsListIsInitialized() {
		assertNotNull(customer.getAccounts());
		assertEquals(customer.getAccounts().size(), 0);
	}
	
	@Test
	public void openAccount_GivenValidAccountType_AccountIsCreated() throws DuplicateAccountException {
		customer.openAccount(AccountType.CHECKING_ACCOUNT);
		
		assertEquals(customer.getAccounts().size(), 1);
	}
	
	@Test
	public void openAccount_GivenNulAccountType_AccountIsNotCreated() throws DuplicateAccountException {
		customer.openAccount(null);
		
		assertEquals(customer.getAccounts().size(), 0);
		
		customer.getAccounts().clear();
	}
	
	@Test
	public void openAccount_CalledMultipleTimes_AccountsAreCreated() throws DuplicateAccountException {
		customer.openAccount(AccountType.CHECKING_ACCOUNT);
		customer.openAccount(AccountType.CHECKING_ACCOUNT);
		customer.openAccount(AccountType.SAVINGS_ACCOUNT);
		
		assertEquals(customer.getAccounts().size(), 3);
		
		customer.getAccounts().clear();
	}
	
	@Test
	public void addAccount_GivenNullAccount_NullAccountIsNotAdded() throws DuplicateAccountException {
		customer.addAccount(null);
		
		assertEquals(customer.getAccounts().size(), 0);
	}
	
	@Test
	public void addAccount_GivenValidAccount_AccountIsAdded() throws DuplicateAccountException {
		Account account = AccountFactory.createAccount(AccountType.CHECKING_ACCOUNT);
		
		customer.addAccount(account);
		
		assertEquals(customer.getAccounts().size(), 1);
	}
	
	@Test
	public void addAccount_GivenValidAccounts_AccountAreAdded() throws DuplicateAccountException {
		customer.getAccounts().clear();
		Account account1 = AccountFactory.createAccount(AccountType.CHECKING_ACCOUNT);
		Account account2 = AccountFactory.createAccount(AccountType.CHECKING_ACCOUNT);
		Account account3 = AccountFactory.createAccount(AccountType.SAVINGS_ACCOUNT);
		Account account4 = AccountFactory.createAccount(AccountType.MAXI_SAVINGS_ACCOUNT);
		
		customer.addAccount(account1);
		customer.addAccount(account2);
		customer.addAccount(account3);
		customer.addAccount(account4);
		
		assertEquals(customer.getAccounts().size(), 4);
	}
	
	@Test(expected=DuplicateAccountException.class)
	public void addAccount_GivenDuplicateAccounts_DuplicateAccountExceptionIsThrown() throws DuplicateAccountException {
		Account account1 = AccountFactory.createAccount(AccountType.CHECKING_ACCOUNT);
		
		customer.addAccount(account1);
		customer.addAccount(account1);
	}
	
	@Test
	public void transferFundByAccount_GivenValidAccountsAndBalance_TransferIsProcessed()
			throws InvalidTransactionAmountException, InvalidAccountIdException, InsufficientFundsException, DuplicateAccountException {
		// SETUP
		customer.getAccounts().clear();
		Account checking = AccountFactory.createAccount(AccountType.CHECKING_ACCOUNT);
		checking.deposit(500);
		customer.addAccount(checking);
		
		Account savings = AccountFactory.createAccount(AccountType.SAVINGS_ACCOUNT);
		customer.addAccount(savings);
		
		// ACTION
		customer.transferFundByAccount(checking, savings, 200);
		
		// ASSERT
		assertTrue(checking.getBalance() == 300);
		assertTrue(savings.getBalance() == 200);
	}
	
	@Test(expected=InsufficientFundsException.class)
	public void transferFundByAccount_GivenInsufficientFundsOnSource_InsufficientFundsExceptionIsThrown()
			throws InvalidTransactionAmountException, InvalidAccountIdException, InsufficientFundsException, DuplicateAccountException {
		// SETUP
		customer.getAccounts().clear();
		
		Account checking = AccountFactory.createAccount(AccountType.CHECKING_ACCOUNT);
		checking.deposit(100);
		customer.addAccount(checking);
		
		Account savings = AccountFactory.createAccount(AccountType.SAVINGS_ACCOUNT);
		customer.addAccount(savings);
		
		// ACTION
		customer.transferFundByAccount(checking, savings, 200);
	}
	
	@Test(expected=InvalidAccountIdException.class)
	public void transferFundByAccount_GivenInvalidSourceAccount_InvalidAccountIdExceptionIsThrown()
			throws InvalidTransactionAmountException, InvalidAccountIdException, InsufficientFundsException, DuplicateAccountException {
		// SETUP
		customer.getAccounts().clear();
		
		// do not add the checking to the customer's accounts
		Account checking = AccountFactory.createAccount(AccountType.CHECKING_ACCOUNT);
		checking.deposit(500);
		
		Account savings = AccountFactory.createAccount(AccountType.SAVINGS_ACCOUNT);
		customer.addAccount(savings);
		
		// ACTION
		customer.transferFundByAccount(checking, savings, 200);
	}
	
	@Test(expected=InvalidAccountIdException.class)
	public void transferFundByAccount_GivenInvalidTargetAccount_InvalidAccountIdExceptionIsThrown()
			throws InvalidTransactionAmountException, InvalidAccountIdException, InsufficientFundsException, DuplicateAccountException {
		// SETUP
		customer.getAccounts().clear();
		
		Account checking = AccountFactory.createAccount(AccountType.CHECKING_ACCOUNT);
		checking.deposit(500);
		customer.addAccount(checking);
		
		// do not add the savings to the customer's accounts
		Account savings = AccountFactory.createAccount(AccountType.SAVINGS_ACCOUNT);
		
		// ACTION
		customer.transferFundByAccount(checking, savings, 200);
	}
	
	@Test
	public void getNumberOfAccounts_WhenCalled_NumberOfAccountsIsReturned() throws DuplicateAccountException {
		// SETUP
		customer.getAccounts().clear();
		Account checking = AccountFactory.createAccount(AccountType.CHECKING_ACCOUNT);
		Account savings = AccountFactory.createAccount(AccountType.SAVINGS_ACCOUNT);
		
		// ACTION
		customer.addAccount(checking).addAccount(savings);
		
		// ACTION
		assertTrue(customer.getNumberOfAccounts() == 2);
	}
	
	@Test
	public void totalInterestEarned_WhenCalled_InterestEarnedByCustomerIsReturned()
			throws InvalidTransactionAmountException, DuplicateAccountException {
		// SETUP
		customer.getAccounts().clear();
		
		Account checking = AccountFactory.createAccount(AccountType.CHECKING_ACCOUNT);
		checking.deposit(1000);
		
		Account savings = AccountFactory.createAccount(AccountType.SAVINGS_ACCOUNT);
		savings.deposit(2000);
		
		customer.addAccount(checking).addAccount(savings);
		
		// ACTION
		assertEquals(customer.totalInterestEarned(), 4, 0);
	}
}
