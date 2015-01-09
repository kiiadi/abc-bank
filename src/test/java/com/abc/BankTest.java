package com.abc;

import static com.abc.Account.Types.CHECKING;
import static com.abc.Account.Types.MAXI_SAVINGS;
import static com.abc.Account.Types.SAVINGS;
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
        prepareCustomerAccounts("John", asList(CHECKING.newInstance()));
        assertEquals("Customer Summary\n - John (1 account)", bank.customerSummary());
    }

    @Test
    public void customerSummary_whenMoreCustomersZeroOrMoreAccounts_thenShowThemInSeperateLines() {
        prepareCustomerAccounts("John", asList(CHECKING.newInstance()));
        prepareCustomerAccounts("Eric", asList(SAVINGS.newInstance(), CHECKING.newInstance()));
        prepareCustomerAccounts("Mary", asList());
        assertEquals("Customer Summary\n - John (1 account)\n - Eric (2 accounts)\n - Mary (0 account)", 
        		bank.customerSummary());
    }
    
    @Test
    public void totalInterestPaid_givenCheckingAccountAndDeposit_thenPayFlatRate() {
    	Account checkingAccount = CHECKING.newInstance();
    	prepareCustomerAccounts("Bill", asList(checkingAccount));
        checkingAccount.deposit(100.0);

        assertAmount(0.1, bank.totalInterestPaid());
    }

    @Test
    public void totalInterestPaid_givenSavingsAccountAndDeposit_thenPayByAmountTiers() {
        Account savingAccount = SAVINGS.newInstance();
    	prepareCustomerAccounts("Bill", asList(savingAccount));
        savingAccount.deposit(1500.0);

        assertAmount(1000*0.001+500*0.002, bank.totalInterestPaid());
    }

    @Test
    public void totalInterestPaid_givenMaxiSavingsAccountAndDeposit_thenPayByAmountTiersWithHigherRates() {
        Account maxiSavings = MAXI_SAVINGS.newInstance();
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
