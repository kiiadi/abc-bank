package com.abc;

import java.util.ArrayList;
import java.util.List;

public class Bank {

	private List<Customer> customers;

	public List<Customer> getCustomers() {
		return customers;
	}

	public Bank() {
		customers = new ArrayList<Customer>();
	}

	public void addCustomer(Customer customer) {
		customers.add(customer);
	}

	public Customer getFirstCustomer() {
		return customers.get(0);
	}

	public String customerSummary() {
		iReportBuilder builder = new BankCustomersSummaryBuilder();
		return builder.build(this);
	}

	public double totalInterestPaid() {
		double total = BankConstants.ZERO_AMOUNT;
		for (Customer customer : customers)
			total += customer.totalInterestEarned();
		return total;
	}

}
