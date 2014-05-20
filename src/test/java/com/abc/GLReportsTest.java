package com.abc;

import static org.junit.Assert.assertEquals;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;

import com.abc.api.BankingServices;
import com.abc.exceptions.GLError;
import com.abc.interfaces.AccountType;
import com.abc.interfaces.BankingSystemFacade;
import com.abc.interfaces.GLReportingApi;
import com.abc.interfaces.GeneralLedgerApi;

public class GLReportsTest {

	private GeneralLedgerApi m_generalLedgerApi;
	private GLReportingApi m_glReportingApi;

	@Before
	public void setup() {
		BankingSystemFacade bankServices = new BankingServices();
		m_generalLedgerApi = bankServices.getGeneralLedgerApi("BankOfAmericaPersonalFinance");
		m_glReportingApi = bankServices.getGLReportingApi();
	}

	@Test
	public void testCustomerStatementSingleAccount() throws GLError {
		m_generalLedgerApi.openAccount("Henry", AccountType.CHECKING);
		m_generalLedgerApi.deposit("Henry", AccountType.CHECKING, new Date(), 100.0);	 
		Customer henry = (Customer) m_generalLedgerApi.getCustomer("Henry");
		assertEquals("Statement for Henry\n" + "\n" + "Checking Account\n" + "  deposit $100.00\n" + "Total $100.00\n"  
		+ "\n" + "Total In All Accounts $100.00",
				m_glReportingApi.getCustomerStatement(henry));
	}	
	
	@Test
	public void testCustomerStatementMultiAccount() throws GLError {
		m_generalLedgerApi.openAccount("Henry", AccountType.CHECKING);
		m_generalLedgerApi.openAccount("Henry", AccountType.SAVINGS);
		m_generalLedgerApi.openAccount("Henry", AccountType.MAXI_SAVINGS);

		m_generalLedgerApi.deposit("Henry", AccountType.CHECKING, new Date(), 100.0);
		m_generalLedgerApi.deposit("Henry", AccountType.SAVINGS, new Date(), 4000.0);
		m_generalLedgerApi.withdraw("Henry", AccountType.SAVINGS, new Date(), 200.0);
		m_generalLedgerApi.deposit("Henry", AccountType.MAXI_SAVINGS, new Date(), 500.0);

		Customer henry = (Customer) m_generalLedgerApi.getCustomer("Henry");

		assertEquals("Statement for Henry\n" + "\n" + "Checking Account\n" + "  deposit $100.00\n" + "Total $100.00\n" + "\n"
				+ "Savings Account\n" + "  deposit $4,000.00\n" + "  withdrawal $200.00\n" + "Total $3,800.00\n" + "\n"
				+ "Maxi Savings Account\n" + "  deposit $500.00\n" + "Total $500.00\n" + "\n" + "Total In All Accounts $4,400.00",
				m_glReportingApi.getCustomerStatement(henry));
	}	
	

	@Test
	public void testGetCustomerSummary() throws GLError {
		m_generalLedgerApi.openAccount("John", AccountType.SAVINGS);
		assertEquals("Customer Summary\n - John (1 account)", m_glReportingApi.getCustomerSummary(m_generalLedgerApi).toString());
		m_generalLedgerApi.openAccount("John", AccountType.CHECKING);
		assertEquals("Customer Summary\n - John (2 accounts)", m_glReportingApi.getCustomerSummary(m_generalLedgerApi).toString());
	}
}
