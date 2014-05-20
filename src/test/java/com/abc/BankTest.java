package com.abc;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;

import com.abc.api.BankingServices;
import com.abc.exceptions.AccountOpenError;
import com.abc.exceptions.CustomerNotFound;
import com.abc.exceptions.InvalidAccount;
import com.abc.exceptions.TransactionAmountIsLessThanZero;
import com.abc.interfaces.AccountDetail;
import com.abc.interfaces.AccountType;
import com.abc.interfaces.BankingSystemFacade;
import com.abc.interfaces.GLReportingApi;
import com.abc.interfaces.GeneralLedgerApi;
import com.abc.interfaces.JournalEntry;
import com.abc.interfaces.TransactionType;

public class BankTest {
	private static final double DOUBLE_DELTA = 1e-15;
 
	private GeneralLedgerApi m_generalLedgerApi;
	private GLReportingApi m_glReportingApi;

	@Before
	public void setup() {
		BankingSystemFacade bankServices = new BankingServices();
		m_generalLedgerApi = bankServices.getGeneralLedgerApi("BankOfAmericaPersonalFinance");
		m_glReportingApi = bankServices.getGLReportingApi();
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
	public void testCheckingInterest() throws AccountOpenError, InvalidAccount, CustomerNotFound, TransactionAmountIsLessThanZero {
		m_generalLedgerApi.openAccount("Bill", AccountType.CHECKING);
		m_generalLedgerApi.deposit("Bill", AccountType.CHECKING, new Date(), 100.0);		
		assertEquals(0.1, m_generalLedgerApi.calculateInterestEarned("Bill", AccountType.CHECKING), DOUBLE_DELTA);
	}

	@Test
	public void testSavingsInterest() throws AccountOpenError, InvalidAccount, CustomerNotFound, TransactionAmountIsLessThanZero {		 		
		m_generalLedgerApi.openAccount("Bill", AccountType.SAVINGS);
		m_generalLedgerApi.deposit("Bill", AccountType.SAVINGS, new Date(), 1500.0);		
		assertEquals(2.0, m_generalLedgerApi.calculateInterestEarned("Bill", AccountType.SAVINGS), DOUBLE_DELTA);
	}

	@Test
	public void testSavingsInterestLess1000() throws AccountOpenError, InvalidAccount, CustomerNotFound, TransactionAmountIsLessThanZero {		 		
		m_generalLedgerApi.openAccount("Bill", AccountType.SAVINGS);
		m_generalLedgerApi.deposit("Bill", AccountType.SAVINGS, new Date(), 1000.0);		
		assertEquals(1.0, m_generalLedgerApi.calculateInterestEarned("Bill", AccountType.SAVINGS), DOUBLE_DELTA);
	}
	
	@Test
	public void testMaxSavingsInterest()throws AccountOpenError, InvalidAccount, CustomerNotFound, TransactionAmountIsLessThanZero {
		m_generalLedgerApi.openAccount("Bill", AccountType.MAXI_SAVINGS);
		m_generalLedgerApi.deposit("Bill", AccountType.MAXI_SAVINGS, new Date(), 3000.0);
		assertEquals(170.0,  m_generalLedgerApi.calculateInterestEarned("Bill", AccountType.MAXI_SAVINGS), DOUBLE_DELTA);
	}
	

	@Test
	public void testMaxSavingsInterestLess1000()throws AccountOpenError, InvalidAccount, CustomerNotFound, TransactionAmountIsLessThanZero {
		m_generalLedgerApi.openAccount("Bill", AccountType.MAXI_SAVINGS);
		m_generalLedgerApi.deposit("Bill", AccountType.MAXI_SAVINGS, new Date(), 1000.0);
		assertEquals(20.0,  m_generalLedgerApi.calculateInterestEarned("Bill", AccountType.MAXI_SAVINGS), DOUBLE_DELTA);
	}
	
	@Test
	public void testMaxSavingsInterestLess2000()throws AccountOpenError, InvalidAccount, CustomerNotFound, TransactionAmountIsLessThanZero {
		m_generalLedgerApi.openAccount("Bill", AccountType.MAXI_SAVINGS);
		m_generalLedgerApi.deposit("Bill", AccountType.MAXI_SAVINGS, new Date(), 2000.0);
		assertEquals(70.0,  m_generalLedgerApi.calculateInterestEarned("Bill", AccountType.MAXI_SAVINGS), DOUBLE_DELTA);
	}
	
	@Test
	public void testGetCustomerSummary() throws AccountOpenError {	 
		m_generalLedgerApi.openAccount("John", AccountType.SAVINGS);	 
		assertEquals("Customer Summary\n - John (1 account)", m_glReportingApi.getCustomerSummary(m_generalLedgerApi).toString());
		m_generalLedgerApi.openAccount("John", AccountType.CHECKING);
		assertEquals("Customer Summary\n - John (2 accounts)", m_glReportingApi.getCustomerSummary(m_generalLedgerApi).toString()); 
	}
}
