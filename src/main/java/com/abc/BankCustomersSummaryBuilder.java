package com.abc;

public class BankCustomersSummaryBuilder implements iReportBuilder {

	public String build(Object object) {
		Bank bank = (Bank)object;
		StringBuilder summaryBuilder = new StringBuilder(BankConstants.CUSTOMER_SUMMARY_HEADING);
		for (Customer customer : bank.getCustomers())
			summaryBuilder.append(customer.getSummary());
		return summaryBuilder.toString();
	}

}
