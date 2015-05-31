package com.abc.transaction;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import com.abc.Customer;

public class Bank {
    private final Collection<Customer> customers;

    public Bank() {
        customers = new ArrayList<Customer>();
    }

    public void addCustomer(Customer customer) {
        customers.add(customer);
    }

    public Collection<Customer> getCustomers() {
        return Collections.unmodifiableCollection(customers);
    }

    public double totalInterestPaid() {
        double total = 0;
        for (Customer customer : customers) {
            total += customer.totalInterestEarned();
        }
        return total;
    }
}
