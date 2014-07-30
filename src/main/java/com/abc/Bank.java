package com.abc;

import java.util.ArrayList;
import java.util.List;

public class Bank {
    private final List<Customer> customers = new ArrayList<Customer>();
    final CustomerSummaryView view = new CustomerSummaryView();

    public List<Customer> getCustomers() {
        return customers;
    }

    public void add(Customer customer) {
        customers.add(customer);
    }

    public double totalInterestPaid() {
        double total = 0;
        for(Customer customer: getCustomers())
            total += customer.totalInterestEarned();
        return total;
    }

}
