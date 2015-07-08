package com.abc;

import com.abc.customer.Customer;
import com.abc.customer.CustomersSummaryGenerator;

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

  public String customerSummary() {
    CustomersSummaryGenerator generator = new CustomersSummaryGenerator(customers);

    return generator.getSummary();
  }

  public double totalInterestPaid() {
    double total = 0;
    for (Customer c : customers) {
      total += c.totalInterestEarned();
    }
    return total;
  }

  public String getFirstCustomer() {
    if (customers.isEmpty()) {
      return "Error";
    }

    return customers.get(0).getName();
  }
}
