package com.abc;

import java.util.List;

/**
 * A report creator that produces a customer summary.
 */
public class CustomerSummaryReportCreator {

    /**
     * Creates a report.
     * @param customers customers to report on
     * @return the report
     */
    public String create(final List<Customer> customers) {
        StringBuilder summary = new StringBuilder("Customer Summary");

        for (Customer customer : customers) {
            String accountString = StringUtils.getNumberDisplayString(customer.getAccounts().size(), "account");
            summary
                .append("\n - ")
                .append(customer.getName())
                .append(" (")
                .append(accountString)
                .append(")");
        }
        return summary.toString();
    }
}
