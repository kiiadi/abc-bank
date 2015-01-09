package com.abc;

import static com.abc.Account.*;
import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;

import java.util.Collection;

import org.junit.Before;
import org.junit.Test;

public class BankTest {
    private Bank bank;
    
    @Before
    public void givenBank() {
    	bank = new Bank();
    }

    @Test
    public void customerSummary_whenNoCustomer_thenShowTitleOnly() {
        assertEquals("Customer Summary", bank.customerSummary());
    }

    @Test
    public void customerSummary_whenOneCustomerAndOneAccount_thenShowItInSeperateLine() {
        prepareCustomerAccounts("John", asList(new Account(CHECKING)));
        assertEquals("Customer Summary\n - John (1 account)", bank.customerSummary());
    }

    @Test
    public void customerSummary_whenMoreCustomersZeroOrMoreAccounts_thenShowThemInSeperateLines() {
        prepareCustomerAccounts("John", asList(new Account(CHECKING)));
        prepareCustomerAccounts("Eric", asList(new Account(SAVINGS), new Account(CHECKING)));
        prepareCustomerAccounts("Mary", asList());
        assertEquals("Customer Summary\n - John (1 account)\n - Eric (2 accounts)\n - Mary (0 account)", 
        		bank.customerSummary());
    }
    
    @Test
    public void totalInterestPaid_givenCheckingAccountAndDeposit_thenPayFlatRate() {
    	Account checkingAccount = new Account(CHECKING);
    	prepareCustomerAccounts("Bill", asList(checkingAccount));
        checkingAccount.deposit(100.0);

        assertAmount(0.1, bank.totalInterestPaid());
    }

    @Test
    public void totalInterestPaid_givenSavingsAccountAndDeposit_thenPayByAmountTiers() {
        Account savingAccount = new Account(SAVINGS);
    	prepareCustomerAccounts("Bill", asList(savingAccount));
        savingAccount.deposit(1500.0);

        assertAmount(1000*0.001+500*0.002, bank.totalInterestPaid());
    }

    @Test
    public void totalInterestPaid_givenMaxiSavingsAccountAndDeposit_thenPayByAmountTiersWithHigherRates() {
        Account maxiSavings = new Account(MAXI_SAVINGS);
        prepareCustomerAccounts("Bill", asList(maxiSavings));
        maxiSavings.deposit(3000.0);

        assertAmount(1000*0.02+1000*0.05+1000*0.1, bank.totalInterestPaid());
    }

	private void prepareCustomerAccounts(String name, Collection<Account> accounts) {
		Customer customer = new Customer(name);
		for (Account account: accounts) {
			customer.openAccount(account);
		}
        bank.addCustomer(customer);
	}
	
	private void assertAmount(double expect, double actual) {
	    final double DOUBLE_DELTA = 1e-15;
        assertEquals(expect, actual, DOUBLE_DELTA);
	}
}
