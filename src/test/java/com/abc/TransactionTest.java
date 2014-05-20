package com.abc;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.Date;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.abc.api.BankingServices;
import com.abc.exceptions.GLError;
import com.abc.exceptions.GLErrorCodes;
import com.abc.interfaces.AccountType;
import com.abc.interfaces.BankingSystemFacade;
import com.abc.interfaces.GeneralLedgerApi;
import com.abc.interfaces.JournalEntry;
import com.abc.interfaces.TransactionType;

public class TransactionTest {
	
	@Rule
	public final ExpectedException rule = ExpectedException.none();
	
	private GeneralLedgerApi m_generalLedgerApi;
	 
	@Before
	public void setup() {
		BankingSystemFacade bankServices = new BankingServices();
		m_generalLedgerApi = bankServices.getGeneralLedgerApi("BankOfAmericaPersonalFinance");		 
	}
	
	@Test 
	public void testTransactionWithZeroAmount() throws GLError {
		rule.expect(GLError.class);
		rule.expect(new ErrorCodeMatcher(GLErrorCodes.GL_TRANSACTION_AMOUNT_ZERO_OR_LESS));
		m_generalLedgerApi.createJournalEntry("Bill", AccountType.CHECKING, new Date(), 0.0, TransactionType.CREDIT);
		m_generalLedgerApi.createJournalEntry("Bill", AccountType.CHECKING, new Date(), -100.0, TransactionType.CREDIT);
	}
	
	@Test 
	public void testTransactionWithNoCustomerExist() throws GLError  {
		rule.expect(GLError.class);
		rule.expect(new ErrorCodeMatcher(GLErrorCodes.GL_CUSTOMER_NOT_FOUND));
		m_generalLedgerApi.createJournalEntry("Bill", AccountType.CHECKING, new Date(), 100.0, TransactionType.CREDIT);	 
	}
	
	@Test 
	public void testTransactionWithInvalidAccountt() throws GLError {
		rule.expect(GLError.class);
		rule.expect(new ErrorCodeMatcher(GLErrorCodes.GL_INVALID_ACCOUNT));
		m_generalLedgerApi.openAccount("Bill", AccountType.CHECKING);
		m_generalLedgerApi.createJournalEntry("Bill", AccountType.SAVINGS, new Date(), 100.0, TransactionType.DEBIT);	 
	}
	
	@Test
	public void testCreditTransaction()  throws GLError  {	 
		m_generalLedgerApi.openAccount("Bill", AccountType.CHECKING);
		JournalEntry t = m_generalLedgerApi.createJournalEntry("Bill", AccountType.CHECKING, new Date(), 100.0, TransactionType.CREDIT);
		assertNotNull(t);
		assertEquals(TransactionType.CREDIT, t.getTransactionType());
		assertEquals(100.0, t.getTransactionAmount(), 0);
		assertNotNull(t.getTransactionDate());
	}

	@Test
	public void testDebitTransaction() throws GLError  {	 
		m_generalLedgerApi.openAccount("Bill", AccountType.SAVINGS);
		JournalEntry t = m_generalLedgerApi.createJournalEntry("Bill", AccountType.SAVINGS, new Date(), 100.0, TransactionType.DEBIT);
		assertNotNull(t);
		assertEquals(TransactionType.DEBIT, t.getTransactionType());
		assertEquals(-100.0, t.getTransactionAmount(), 0);
		assertNotNull(t.getTransactionDate());
	}

	@Test
	public void testCheckingInterest() throws GLError  {
		m_generalLedgerApi.openAccount("Bill", AccountType.CHECKING);
		m_generalLedgerApi.deposit("Bill", AccountType.CHECKING, new Date(), 100.0);		
		assertEquals(0.1, m_generalLedgerApi.calculateInterestEarned("Bill", AccountType.CHECKING), 0);
	}

	@Test
	public void testSavingsInterest() throws GLError  {		 		
		m_generalLedgerApi.openAccount("Bill", AccountType.SAVINGS);
		m_generalLedgerApi.deposit("Bill", AccountType.SAVINGS, new Date(), 1500.0);		
		assertEquals(2.0, m_generalLedgerApi.calculateInterestEarned("Bill", AccountType.SAVINGS), 0);
	}

	@Test
	public void testSavingsInterestLess1000() throws GLError  {		 		
		m_generalLedgerApi.openAccount("Bill", AccountType.SAVINGS);
		m_generalLedgerApi.deposit("Bill", AccountType.SAVINGS, new Date(), 1000.0);		
		assertEquals(1.0, m_generalLedgerApi.calculateInterestEarned("Bill", AccountType.SAVINGS), 0);
	}
	
	@Test
	public void testMaxSavingsInterest() throws GLError {
		m_generalLedgerApi.openAccount("Bill", AccountType.MAXI_SAVINGS);
		m_generalLedgerApi.deposit("Bill", AccountType.MAXI_SAVINGS, new Date(), 3000.0);
		assertEquals(170.0,  m_generalLedgerApi.calculateInterestEarned("Bill", AccountType.MAXI_SAVINGS), 0);
	}
	

	@Test
	public void testMaxSavingsInterestLess1000() throws GLError  {
		m_generalLedgerApi.openAccount("Bill", AccountType.MAXI_SAVINGS);
		m_generalLedgerApi.deposit("Bill", AccountType.MAXI_SAVINGS, new Date(), 1000.0);
		assertEquals(20.0,  m_generalLedgerApi.calculateInterestEarned("Bill", AccountType.MAXI_SAVINGS), 0);
	}
	
	@Test
	public void testMaxSavingsInterestLess2000()throws GLError  {
		m_generalLedgerApi.openAccount("Bill", AccountType.MAXI_SAVINGS);
		m_generalLedgerApi.deposit("Bill", AccountType.MAXI_SAVINGS, new Date(), 2000.0);
		assertEquals(70.0,  m_generalLedgerApi.calculateInterestEarned("Bill", AccountType.MAXI_SAVINGS), 0);
	}
}
