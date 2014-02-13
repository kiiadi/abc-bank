package com.abc;

import java.util.ArrayList;
import java.util.List;

public class Bank {
    public List<Customer> getCustomers() {
        return customers;
    }

    List<Customer> customers;

    public Bank() {
        customers = new ArrayList<Customer>();
    }


    protected void openAccount(String customerName, List<Account> accounts) {
        Customer newCustomer = new Customer(customerName, accounts);
        customers.add(newCustomer);
    }

    public String customerSummary() {
        String summary = "Customer Summary";
        for (Customer customer : customers)
            summary += "\n - " + customer.getName() + " ( Number of accounts are " + customer.getNumberOfAccounts() + " )";
        return summary;
    }

    public double totalInterestPaid() {
        double totalInterest = 0;
        for (Customer customer : customers) {

            totalInterest += customer.getTotalInterestEarned();
        }

        return totalInterest;

    }


}
