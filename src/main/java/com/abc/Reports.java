package com.abc;

import java.util.List;

public class Reports {
	public static String customerSummary(List<Customer> customers) {
		return new BankCustomerSummaryReport().report(customers);
	}
	
	public static String totalInterestPaidSummary(Bank bank) {
		return String.format("Total Interest Paid Summary: %.2f", bank.totalInterestPaid());
	}
}

class BankCustomerSummaryReport {
	public String report(List<Customer> customers) {
	    String summary = "Customer Summary";
	    for (Customer c : customers)
	        summary += String.format("\n - %s (%d %s)", c.getName(), c.getNumberOfAccounts(), formatAccountLabel(c.getNumberOfAccounts()));
	    return summary;
	}
	
	private String formatAccountLabel(int number) {
		return (number<=1) ? "account" : "accounts";
	}
}
