package com.abc;

import com.abc.util.ReportFormatterHelper;

import java.util.ArrayList;
import java.util.List;

public class Bank {
    private List<Customer> customers;

    public Bank() {
        customers = new ArrayList<Customer>();
    }

    public void addCustomer(Customer customer) {
        customers.add(customer);
    }

    public double totalInterestPaidAllCustomers() {
        double total = 0;
        for(Customer c: customers)
            total += c.totalInterestEarned();
        return total;
    }

    public String customerSummary() {

        StringBuilder summary = new StringBuilder("Customer Summary");
        for (Customer c : customers)
            summary .append("\n - " ).append( c.getName() ).append(" (").append(ReportFormatterHelper.format(c.getNumberOfAccounts(), "account")).append(")");
        return summary.toString();
    }

}
