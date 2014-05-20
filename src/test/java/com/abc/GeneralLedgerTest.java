package com.abc;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.abc.api.BankingServices;
import com.abc.exceptions.GLError;
import com.abc.exceptions.GLErrorCodes;
import com.abc.interfaces.AccountDetail;
import com.abc.interfaces.AccountType;
import com.abc.interfaces.BankingSystemFacade;
import com.abc.interfaces.GLReportingApi;
import com.abc.interfaces.GeneralLedgerApi;

public class GeneralLedgerTest {
	
	
	@Rule
	public final ExpectedException rule = ExpectedException.none();
	
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
	public void testOpenNewAccount() throws GLError {		
		AccountDetail account = m_generalLedgerApi.openAccount("Bill", AccountType.CHECKING);
		assertEquals(AccountType.CHECKING, account.getAccountType());
	}

	@Test
	public void testOpenMultipleAccounts() throws GLError {		
		AccountDetail account = m_generalLedgerApi.openAccount("Bill", AccountType.CHECKING);
		assertEquals(AccountType.CHECKING, account.getAccountType());
		account = m_generalLedgerApi.openAccount("Bill", AccountType.SAVINGS);
		assertEquals(AccountType.SAVINGS, account.getAccountType());
	}

	@Test
	public void testOpenExistingAccount() throws GLError {		
		rule.expect(GLError.class);
		rule.expect(new ErrorCodeMatcher(GLErrorCodes.GL_ACCOUNT_OPEN_ERROR));
		m_generalLedgerApi.openAccount("Bill", AccountType.CHECKING);
		m_generalLedgerApi.openAccount("Bill", AccountType.CHECKING);		
	}

}
