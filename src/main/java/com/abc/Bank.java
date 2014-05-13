package com.abc;

import java.util.*;

public class Bank {
  private List<Customer> customers  = Collections.synchronizedList(new ArrayList<Customer>());

  public void addCustomer(Customer customer) {
    customers.add(customer);
  }

  public String customerSummary() {
    StringBuilder summary = new StringBuilder().append("Customer Summary\n");
    for(Customer customer : customers) {
      summary.append(String.format(" - %s (%d account%s)\n",
                                    customer.getName(),
                                    customer.getNumberOfAccounts(),
                                    customer.getNumberOfAccounts() > 1 ? "s" : ""));
    }
    return summary.toString();
  }

  public double totalInterestPaid() {
    double total = 0;
    for(Customer customer : customers)
      total += customer.totalInterestEarned();
    return total;
  }
}
