package com.abc;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;

import com.abc.api.IocServiceContainer;
import com.abc.exceptions.AccountOpenError;
import com.abc.exceptions.CustomerNotFound;
import com.abc.exceptions.InvalidAccount;
import com.abc.exceptions.TransactionAmountIsLessThanZero;
import com.abc.interfaces.AccountDetail;
import com.abc.interfaces.AccountType;
import com.abc.interfaces.BankSystemServices;
import com.abc.interfaces.GeneralLedgerApi;
import com.abc.interfaces.JournalEntry;
import com.abc.interfaces.TransactionType;

public class BankTest {
	private static final double DOUBLE_DELTA = 1e-15;
	private BankSystemServices m_bankServices;
	private GeneralLedgerApi m_generalLedgerApi;

	@Before
	public void setup() {
		m_bankServices = new IocServiceContainer();
		m_generalLedgerApi = m_bankServices.getGeneralLedgerApi("BankOfAmericaPersonalFinance");
	}

	@Test
	public void testCreateGeneralLedger() {
		assertNotNull(m_generalLedgerApi.getBankDetail());
		assertEquals("BankOfAmericaPersonalFinance", m_generalLedgerApi.getBankDetail().getCode());
	}

	@Test
	public void testOpenNewAccount() throws AccountOpenError {		
		AccountDetail account = m_generalLedgerApi.openAccount("Bill", AccountType.CHECKING);
		assertEquals(AccountType.CHECKING, account.getAccountType());
	}

	@Test
	public void testOpenMultipleAccounts() throws AccountOpenError {		
		AccountDetail account = m_generalLedgerApi.openAccount("Bill", AccountType.CHECKING);
		assertEquals(AccountType.CHECKING, account.getAccountType());
		account = m_generalLedgerApi.openAccount("Bill", AccountType.SAVINGS);
		assertEquals(AccountType.SAVINGS, account.getAccountType());
	}

	@Test(expected = AccountOpenError.class)
	public void testOpenExistingAccount() throws AccountOpenError {		
		m_generalLedgerApi.openAccount("Bill", AccountType.CHECKING);
		m_generalLedgerApi.openAccount("Bill", AccountType.CHECKING);		
	}
	
	@Test(expected = TransactionAmountIsLessThanZero.class)
	public void testTransactionWithZeroAmount() throws InvalidAccount, CustomerNotFound, TransactionAmountIsLessThanZero {
		m_generalLedgerApi.createJournalEntry("Bill", AccountType.CHECKING, new Date(), 0.0, TransactionType.CREDIT);
		m_generalLedgerApi.createJournalEntry("Bill", AccountType.CHECKING, new Date(), -100.0, TransactionType.CREDIT);
	}
	
	@Test(expected = CustomerNotFound.class)
	public void testTransactionWithNoCustomerExist() throws InvalidAccount, CustomerNotFound, TransactionAmountIsLessThanZero {
		m_generalLedgerApi.createJournalEntry("Bill", AccountType.CHECKING, new Date(), 100.0, TransactionType.CREDIT);	 
	}
	
	@Test(expected = InvalidAccount.class)
	public void testTransactionWithInvalidAccountt() throws InvalidAccount, CustomerNotFound, AccountOpenError, TransactionAmountIsLessThanZero {	 
		m_generalLedgerApi.openAccount("Bill", AccountType.CHECKING);
		m_generalLedgerApi.createJournalEntry("Bill", AccountType.SAVINGS, new Date(), 100.0, TransactionType.DEBIT);	 
	}
	
	@Test
	public void testCreditTransaction() throws InvalidAccount, CustomerNotFound, AccountOpenError, TransactionAmountIsLessThanZero {	 
		m_generalLedgerApi.openAccount("Bill", AccountType.CHECKING);
		JournalEntry t = m_generalLedgerApi.createJournalEntry("Bill", AccountType.CHECKING, new Date(), 100.0, TransactionType.CREDIT);
		assertNotNull(t);
		assertEquals(TransactionType.CREDIT, t.getTransactionType());
		assertEquals(100.0, t.getTransactionAmount(), 0);
		assertNotNull(t.getTransactionDate());
	}

	@Test
	public void testDebitTransaction() throws InvalidAccount, CustomerNotFound, AccountOpenError, TransactionAmountIsLessThanZero {	 
		m_generalLedgerApi.openAccount("Bill", AccountType.SAVINGS);
		JournalEntry t = m_generalLedgerApi.createJournalEntry("Bill", AccountType.SAVINGS, new Date(), 100.0, TransactionType.DEBIT);
		assertNotNull(t);
		assertEquals(TransactionType.DEBIT, t.getTransactionType());
		assertEquals(-100.0, t.getTransactionAmount(), 0);
		assertNotNull(t.getTransactionDate());
	}

	@Test
	public void CheckingAccount() throws AccountOpenError, InvalidAccount, CustomerNotFound, TransactionAmountIsLessThanZero {
		m_generalLedgerApi.openAccount("Bill", AccountType.CHECKING);
		m_generalLedgerApi.deposit("Bill", AccountType.CHECKING, new Date(), 100.0);		
		assertEquals(0.1, ((Bank)m_generalLedgerApi.getBankDetail()).totalInterestPaid(), DOUBLE_DELTA);
	}

	@Test
	public void customerSummary() {
		Bank bank = new Bank();
		Customer john = new Customer("John");
		john.openAccount(new Account(AccountType.CHECKING));
		bank.addCustomer(john);
		assertEquals("Customer Summary\n - John (1 account)", bank.customerSummary());
	}

	@Test
	public void savings_account() throws AccountOpenError, InvalidAccount, CustomerNotFound, TransactionAmountIsLessThanZero {		 		
		m_generalLedgerApi.openAccount("Bill", AccountType.SAVINGS);
		m_generalLedgerApi.deposit("Bill", AccountType.SAVINGS, new Date(), 1500.0);		
		assertEquals(2.0, ((Bank)m_generalLedgerApi.getBankDetail()).totalInterestPaid(), DOUBLE_DELTA);
	}

	@Test
	public void maxi_savings_account()throws AccountOpenError, InvalidAccount, CustomerNotFound, TransactionAmountIsLessThanZero {
		m_generalLedgerApi.openAccount("Bill", AccountType.MAXI_SAVINGS);
		m_generalLedgerApi.deposit("Bill", AccountType.MAXI_SAVINGS, new Date(), 3000.0);
		assertEquals(170.0, ((Bank)m_generalLedgerApi.getBankDetail()).totalInterestPaid(), DOUBLE_DELTA);
	}
	

}
