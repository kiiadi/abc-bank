package com.abc;

import java.util.LinkedList;
import java.util.List;

public class Bank {
    private final List<Customer> customers;

    public Bank() {
        customers = new LinkedList<>();
    }

    public void addCustomer(Customer customer) {
        customers.add(customer);
    }

    public String customerSummary() {
        StringBuilder summary = new StringBuilder("Customer Summary");
        for (Customer c : customers)
            summary.append(System.lineSeparator()).append(" - ").append(c.getName()).append(" (").append(format(c.getNumberOfAccounts(), "account")).append(")");
        return summary.toString();
    }

    /*
    Make sure correct plural of word is created based on the number passed in:
    If number passed in is 1 just return the word otherwise add an 's' at the end
    */
    private String format(int number, String word) {
        return number + " " + (number == 1 ? word : word + "s");
    }

    public double totalInterestPaid() {
        return customers.stream().mapToDouble(Customer::totalInterestEarned).sum() ;
    }

}
