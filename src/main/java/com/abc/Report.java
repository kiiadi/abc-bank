package com.abc;

import java.util.Collection;

public class Report {

    private final Collection<Customer> customers;

    public Report(Collection<Customer> customers) {
        this.customers = customers;
    }

    public String customerSummary() {
        StringBuilder summary = new StringBuilder("Customer Summary");
        for (Customer customer : customers) {
            summary.append("\n - ")
                    .append(customer.getName())
                    .append(" (")
                    .append(format(customer.getAccounts().size(), "account"))
                    .append(')');
        }
        return summary.toString();
    }

    //Make sure correct plural of word is created based on the number passed in:
    //If number passed in is 1 just return the word otherwise add an 's' at the end
    private static String format(int number, String word) {
        return number + " " + (number == 1 ? word : word + "s");
    }
}
