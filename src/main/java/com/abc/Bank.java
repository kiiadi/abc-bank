package com.abc;

import java.util.ArrayList;
import java.util.List;

import com.abc.util.BankUtils;

public class Bank {
	private List<Customer> customers;

	public Bank() {
		customers = new ArrayList<Customer>();
	}

	public void addCustomer(Customer customer) {
		customers.add(customer);
	}

	public String customerSummary() {
		StringBuilder summary = new StringBuilder();
		summary.append("Customer Summary");
		for (Customer c : customers) {
			summary.append("\n - ")
					.append(c.getName())
					.append(" (")
					.append(BankUtils.format(c.getNumberOfAccounts(), "account"))
					.append(")");
		}
		return summary.toString();
	}

	public double totalInterestPaid() {
		double total = 0;
		for (Customer c : customers)
			total += c.totalInterestEarned();
		return total;
	}

	public Customer getFirstCustomer() {
		return customers.get(0);
	}
}
