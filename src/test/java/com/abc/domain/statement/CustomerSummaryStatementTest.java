package com.abc.domain.statement;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.Test;

import com.abc.domain.account.CheckingAccount;
import com.abc.domain.account.SavingsAccount;
import com.abc.domain.bank.Customer;

public class CustomerSummaryStatementTest {

	@Test
	public void testGenerateOneCustomerOneAccount() {
		List<Customer> customers = new ArrayList<Customer>();
		customers.add(new Customer("John").openAccount(new CheckingAccount()));
		
		Statement statement = new CustomerSummaryStatement(customers);
		
		String expected = "Customer Summary\n" +
						  " - John (1 account)";
		
		assertThat(statement.generate(), is(expected));
	}
	
	@Test
	public void testGenerateOneCustomerTwoAccounts() {
		List<Customer> customers = new ArrayList<Customer>();
		customers.add(new Customer("John").openAccount(new CheckingAccount()).openAccount(new SavingsAccount()));
		
		Statement statement = new CustomerSummaryStatement(customers);
		
		String expected = "Customer Summary\n" +
						  " - John (2 accounts)";
		
		assertThat(statement.generate(), is(expected));
	}
	
	@Test
	public void testGenerateTwoCustomer() {
		List<Customer> customers = new ArrayList<Customer>();
		customers.add(new Customer("John").openAccount(new CheckingAccount()).openAccount(new SavingsAccount()));
		customers.add(new Customer("Oscar").openAccount(new CheckingAccount()));
		
		Statement statement = new CustomerSummaryStatement(customers);
		
		String expected = "Customer Summary\n" +
						  " - John (2 accounts)\n" +
						  " - Oscar (1 account)";
		
		assertThat(statement.generate(), is(expected));
	}
	
	@Test
	public void testFormatNumberOfAccount() throws Exception {
		CustomerSummaryStatement statement = new CustomerSummaryStatement(Collections.<Customer>emptyList());
		
		assertThat(statement.formatNumberOfAccounts(0), is("0 accounts"));
		assertThat(statement.formatNumberOfAccounts(1), is("1 account"));
		assertThat(statement.formatNumberOfAccounts(2), is("2 accounts"));
		assertThat(statement.formatNumberOfAccounts(10), is("10 accounts"));
	}
}
