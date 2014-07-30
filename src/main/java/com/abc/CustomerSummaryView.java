package com.abc;

import static com.abc.StringUtils.pluralize;

public class CustomerSummaryView {

    public String render(Bank bank) {
        String summary = "Customer Summary";
        for (Customer customer : bank.getCustomers())
            summary += renderSingleCustomerSummary(customer);
        return summary;
    }

    private String renderSingleCustomerSummary(Customer customer) {
        final int numberOfAccounts = customer.getNumberOfAccounts();
        return "\n - " + customer.getName() + " (" + numberOfAccounts + " " + pluralize("account", numberOfAccounts) + ")";
    }
}
