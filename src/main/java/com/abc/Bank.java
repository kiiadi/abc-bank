package com.abc;

import java.util.*;

public class Bank {
  private List<Customer> customers  = Collections.synchronizedList(new ArrayList<Customer>());

  public void addCustomer(Customer customer) {
    customers.add(customer);
  }

  public String customerSummary() {
    String summary = "Customer Summary";
    for(Customer customer : customers) {
      summary += String.format("\n - %s (%d account%s)",
                                customer.getName(),
                                customer.getNumberOfAccounts(),
                                customer.getNumberOfAccounts() > 1 ? "s" : "");
    }
    return summary;
  }

  public double totalInterestPaid() {
    double total = 0;
    for(Customer customer : customers)
      total += customer.totalInterestEarned();
    return total;
  }
}
