package com.abc.customer;

import java.util.List;

public class CustomersSummaryGenerator {
  private final StringBuilder summary = new StringBuilder();

  public CustomersSummaryGenerator(List<Customer> customers) {
    summary.append("Customer Summary");

    addCustomersSummary(customers);
  }

  private void addCustomersSummary(List<Customer> customers) {
    for (Customer c : customers) {
      summary
        .append("\n")
        .append(" - ")
        .append(c.getName())
        .append(" ")
        .append("(")
        .append(format(c.getNumberOfAccounts(), "account"))
        .append(")");
    }
  }

  //Make sure correct plural of word is created based on the number passed in:
  //If number passed in is 1 just return the word otherwise add an 's' at the end
  private String format(int number, String word) {
    return number + " " + (number == 1 ? word : word + "s");
  }

  public String getSummary() {
    return summary.toString();
  }
}
