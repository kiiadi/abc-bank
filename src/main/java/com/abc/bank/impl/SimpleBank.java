package com.abc.bank.impl;

import com.abc.bank.Bank;
import com.abc.customer.Customer;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Archana on 9/16/14.
 */public class SimpleBank implements Bank {
  private List<Customer> customers;

  public SimpleBank() {
    customers = new ArrayList<Customer>();
  }

  @Override
  public void addCustomer(Customer customer) {
    customers.add(customer);
  }

  @Override
  public String customerSummary() {
    String summary = "Customer Summary";
    for (Customer c : customers)
      summary += "\n - " + c.getName() + " (" + format(c.getNumberOfAccounts(), "account") + ")";
    return summary;
  }

  //Make sure correct plural of word is created based on the number passed in:
  //If number passed in is 1 just return the word otherwise add an 's' at the end
  private String format(int number, String word) {
    return number + " " + (number == 1 ? word : word + "s");
  }

  @Override
  public double totalInterestPaid() {
    BigDecimal total = new BigDecimal("0.0");
    for(Customer c: customers)
      total = total.add(c.totalInterestEarned());
    return total.doubleValue();
  }

  @Override
  public String getFirstCustomer() {
     if(customers.size() > 0)
        return customers.get(0).getName();
    else
       return "No Customers";
  }
}
