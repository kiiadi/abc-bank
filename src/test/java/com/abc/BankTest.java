package com.abc;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.abc.api.IocServiceContainer;
import com.abc.exceptions.AccountOpenError;
import com.abc.interfaces.AccountDetail;
import com.abc.interfaces.BankSystemServices;
import com.abc.interfaces.CustomerDetail;
import com.abc.interfaces.GeneralLedgerApi;

public class BankTest {
    private static final double DOUBLE_DELTA = 1e-15;
    private BankSystemServices m_bankServices;
    
    @Before
    public void setup(){
    	m_bankServices = new IocServiceContainer();
    }

    @Test
    public void testCreateGeneralLedger() {
    	GeneralLedgerApi glApi = m_bankServices.getGeneralLedgerApi("BankOfAmericaPersonalFinance");
    	assertNotNull(glApi.getInfo());
    	assertEquals("BankOfAmericaPersonalFinance", glApi.getInfo().getCode());    	
    }
    
    @Test
    public void testOpenNewAccount() {
    	GeneralLedgerApi glApi = m_bankServices.getGeneralLedgerApi("BankOfAmericaPersonalFinance"); 
    	 try {
			AccountDetail account = glApi.openAccount("Bill", Account.CHECKING);
			assertEquals(Account.CHECKING, account.getAccountType());
		} catch (Exception e) {
			fail(e.getMessage());
		}
    	     	 
    	//glApi.createJournalEntry("Bill", new Date(), 100.0, TransactionType.CREDIT);
    	
    }
    
    @Test
    public void testOpenExistingAccount() {
    	GeneralLedgerApi glApi = m_bankServices.getGeneralLedgerApi("BankOfAmericaPersonalFinance"); 
    	 
    	 try {
    		glApi.openAccount("Bill", Account.CHECKING);
 			glApi.openAccount("Bill", Account.CHECKING);
 			fail("account already created");
 		} catch (AccountOpenError e) {
 			assertTrue(true);
 		}
    	//glApi.createJournalEntry("Bill", new Date(), 100.0, TransactionType.CREDIT);
    	
    }
    
    @Test
    public void testCheckingDeposit() {
    	GeneralLedgerApi glApi = m_bankServices.getGeneralLedgerApi("BankOfAmericaPersonalFinance");
    	
    	//glApi.createJournalEntry("Bill", new Date(), 100.0, TransactionType.CREDIT);
    	
    }
    
    @Test
    public void CheckingAccount() {
    	 
        Bank bank = new Bank();
        Account checkingAccount = new Account(Account.CHECKING);
        Customer bill = new Customer("Bill").openAccount(checkingAccount);
        bank.addCustomer(bill);

        checkingAccount.deposit(100.0);

        assertEquals(0.1, bank.totalInterestPaid(), DOUBLE_DELTA);
    }

    @Test
    public void customerSummary() {
        Bank bank = new Bank();
        Customer john = new Customer("John");
        john.openAccount(new Account(Account.CHECKING));
        bank.addCustomer(john);

        assertEquals("Customer Summary\n - John (1 account)", bank.customerSummary());
    }


    @Test
    public void savings_account() {
        Bank bank = new Bank();
        Account checkingAccount = new Account(Account.SAVINGS);
        bank.addCustomer(new Customer("Bill").openAccount(checkingAccount));

        checkingAccount.deposit(1500.0);

        assertEquals(2.0, bank.totalInterestPaid(), DOUBLE_DELTA);
    }

    @Test
    public void maxi_savings_account() {
        Bank bank = new Bank();
        Account checkingAccount = new Account(Account.MAXI_SAVINGS);
        bank.addCustomer(new Customer("Bill").openAccount(checkingAccount));

        checkingAccount.deposit(3000.0);

        assertEquals(170.0, bank.totalInterestPaid(), DOUBLE_DELTA);
    }

}
