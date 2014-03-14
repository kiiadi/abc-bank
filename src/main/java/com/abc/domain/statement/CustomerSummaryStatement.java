package com.abc.domain.statement;

import java.util.List;

import com.abc.domain.bank.Customer;

public class CustomerSummaryStatement extends AbstractStatement {

	private List<Customer> customers;
	
	public CustomerSummaryStatement(List<Customer> customers) {
		this.customers = customers;
	}

	@Override
	public String generate() {
		String summary = "Customer Summary";
		for (Customer customer : customers)
			summary += NEW_LINE + " - " + customer.getName() + " (" + formatNumberOfAccounts(customer.getNumberOfAccounts()) + ")";
		return summary;
	}
	
	String formatNumberOfAccounts(int numberOfAccounts) {
		return numberOfAccounts + " " + (numberOfAccounts == 1 ? "account" : "accounts");
	}
}
