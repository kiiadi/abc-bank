package com.abc;

import java.util.ArrayList;
import java.util.List;

public class Bank {
  private List<Customer> customers  = new ArrayList<Customer>();

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

  //TODO: find out if getFirstCustomer() is used outside of this package remove if unused
  @Deprecated
  public String getFirstCustomer() {
    try {
      if (customers.size() > 0)
        return customers.get(0).getName();
    } catch (Exception e){
      e.printStackTrace();
    }
    return "Error";
  }
}
