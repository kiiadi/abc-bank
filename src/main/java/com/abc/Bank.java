package com.abc;

import java.math.BigDecimal;
import java.math.BigInteger;
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
        StringBuilder summary = new StringBuilder("Customer Summary");
        for (Customer c : customers) {
            summary.append("\n - ");
            summary.append(c.getName());
            summary.append(" (");
            summary.append(Utils.correctPluralOfWord(c.getNumberOfAccounts(), "account"));
            summary.append(")");
        }
        return summary.toString();
    }

    public BigDecimal totalInterestPaid() {
        BigDecimal total = new BigDecimal(BigInteger.ZERO);
        for (Customer c : customers) {
            total = total.add(c.totalInterestEarned());
        }
        return total;
    }

}
