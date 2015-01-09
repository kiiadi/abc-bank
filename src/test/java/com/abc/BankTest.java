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
    	prepareCustomerAccounts("Bill", asList(deposite(100.0, CHECKING.newInstance())));

        assertAmount(0.1, bank.totalInterestPaid());
    }

    @Test
    public void totalInterestPaid_givenSavingsAccountAndDeposit_thenPayByAmountTiers() {
        prepareCustomerAccounts("Bill", asList(deposite(1500.0, SAVINGS.newInstance())));

        assertAmount(1000*0.001+500*0.002, bank.totalInterestPaid());
    }

    @Test
    public void totalInterestPaid_givenMaxiSavingsAccountAndDeposit_thenPayByAmountTiersWithHigherRates() {
        prepareCustomerAccounts("Bill", asList(deposite(3000.0, MAXI_SAVINGS.newInstance())));

        //assertAmount(1000*0.02+1000*0.05+1000*0.1, bank.totalInterestPaid());
        assertAmount(3000*0.001, bank.totalInterestPaid()); //apply lower rate due to recent withdraw
    }
    
    @Test
    public void totalInterestPaidReport_whenMoreCustomersZeroOrMoreAccounts_thenShowThemInSeperateLines() {
        prepareCustomerAccounts("Bill", asList(deposite(100.0, CHECKING.newInstance())));
        prepareCustomerAccounts("Eric", asList(
        		deposite(100.0, CHECKING.newInstance()),
        		deposite(100.0, CHECKING.newInstance())
        		));
        prepareCustomerAccounts("Mary", asList());
        assertEquals("Total Interest Paid Summary: 0.30", 
        		bank.totalInterestPaidSummary());
    }
    

	private void prepareCustomerAccounts(String name, Collection<Account> accounts) {
		Customer customer = new Customer(name);
		for (Account account: accounts) {
			customer.openAccount(account);
		}
        bank.addCustomer(customer);
	}
	
	private Account deposite(double amount, Account account) {
		account.deposit(amount);
		return account;
	}
	
	private void assertAmount(double expect, double actual) {
	    assertEquals(expect, actual, 1e-15);
	}
}
