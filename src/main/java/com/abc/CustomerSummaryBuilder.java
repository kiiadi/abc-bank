package com.abc;

public class CustomerSummaryBuilder implements iReportBuilder {

	public String build(Object object) {
		Customer customer = (Customer) object;
		StringBuilder summaryBuilder = new StringBuilder ();
		summaryBuilder.append( "\n - ") .append( customer.getName()) .append(" (")
				.append( Utility.format(customer.getNumberOfAccounts(), "account")) .append( ")");
		return summaryBuilder.toString();
	}

}
