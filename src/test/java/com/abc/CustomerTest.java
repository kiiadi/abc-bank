package com.abc;

import java.util.Date;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import com.abc.api.IocServiceContainer;
import com.abc.exceptions.AccountOpenError;
import com.abc.exceptions.CustomerNotFound;
import com.abc.exceptions.InvalidAccount;
import com.abc.exceptions.TransactionAmountIsLessThanZero;
import com.abc.interfaces.AccountType;
import com.abc.interfaces.BankSystemServices;
import com.abc.interfaces.GeneralLedgerApi;

import static org.junit.Assert.assertEquals;

public class CustomerTest {
	private BankSystemServices m_bankServices;
	private GeneralLedgerApi m_generalLedgerApi;

	@Before
	public void setup() {
		m_bankServices = new IocServiceContainer();
		m_generalLedgerApi = m_bankServices.getGeneralLedgerApi("BankOfAmericaPersonalFinance");
	}
    @Test //Test customer statement generation
    public void testApp() throws AccountOpenError, InvalidAccount, CustomerNotFound, TransactionAmountIsLessThanZero{
    	m_generalLedgerApi.openAccount("Henry", AccountType.CHECKING);
    	m_generalLedgerApi.openAccount("Henry", AccountType.SAVINGS);
		
    	m_generalLedgerApi.deposit("Henry", AccountType.CHECKING, new Date(), 100.0);
    	m_generalLedgerApi.deposit("Henry", AccountType.SAVINGS, new Date(), 4000.0);
    	m_generalLedgerApi.withdraw("Henry", AccountType.SAVINGS, new Date(), 200.0);
          	
    	Customer henry = (Customer) m_generalLedgerApi.getCustomer("Henry");

        assertEquals("Statement for Henry\n" +
                "\n" +
                "Checking Account\n" +
                "  deposit $100.00\n" +
                "Total $100.00\n" +
                "\n" +
                "Savings Account\n" +
                "  deposit $4,000.00\n" +
                "  withdrawal $200.00\n" +
                "Total $3,800.00\n" +
                "\n" +
                "Total In All Accounts $3,900.00", henry.getStatement());
    }

    @Test
    public void testOneAccount(){
        Customer oscar = new Customer("Oscar").openAccount(new Account(AccountType.SAVINGS));
        assertEquals(1, oscar.getNumberOfAccounts());
    }

    @Test
    public void testTwoAccount(){
        Customer oscar = new Customer("Oscar")
                .openAccount(new Account(AccountType.SAVINGS));
        oscar.openAccount(new Account(AccountType.CHECKING));
        assertEquals(2, oscar.getNumberOfAccounts());
    }

    @Ignore
    public void testThreeAcounts() {
        Customer oscar = new Customer("Oscar")
                .openAccount(new Account(AccountType.SAVINGS));
        oscar.openAccount(new Account(AccountType.CHECKING));
        assertEquals(3, oscar.getNumberOfAccounts());
    }
}
