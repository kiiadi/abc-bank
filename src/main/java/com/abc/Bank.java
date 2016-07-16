package com.abc;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Bank {
    private final List<Customer> customers;

    public Bank() {
        customers = new ArrayList<Customer>();
    }

    public void addCustomer(Customer customer) {
        customers.add(customer);
    }

    public String customerSummary() {
        String summary = "Customer Summary";
        for (Customer c : customers)
            summary += "\n - " + c.getName() + " (" + (c.getNumberOfAccounts() == 1? "account":"accounts") + ")";
        return summary;
    }

    public BigDecimal totalInterestPaid() {
        BigDecimal total = new BigDecimal(0.0);
        for(Customer c: customers)
            total = total.add(c.totalInterestEarned());
        return total;
    }

}
