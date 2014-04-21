package com.abc.bank;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.abc.accounts.Account;
import com.abc.accounts.AccountFactory;
import com.abc.accounts.AccountType;
import com.abc.customer.Customer;

/**
 * Test-cases follow the following method-name pattern:
 * methodName_Scenario_ExpectedResult()
 *
 */
public class BankReportTest {
	
	@Test
	public void customerSummary_WhenCalledWithTwoAccounts_CustomerSummaryReportIsGenerated() throws Exception{
		Bank bank = new Bank();
		
		Customer john = new Customer(1, "John");
		
		Account checking = AccountFactory.createAccount(AccountType.CHECKING_ACCOUNT);
		Account savings = AccountFactory.createAccount(AccountType.SAVINGS_ACCOUNT);
		
		john.addAccount(checking).addAccount(savings);
		bank.addCustomer(john);
		
		assertEquals("Customer Summary\n - John:1 (2 accounts)", BankReport.customerSummary(bank.getCustomers()));
	}

}
