package com.abc.customer;

import java.util.List;

public class CustomersSummaryGenerator {
  private String summary;

  public CustomersSummaryGenerator(List<Customer> customers) {
    summary = "Customer Summary";

    addCustomersSummary(customers);
  }

  private void addCustomersSummary(List<Customer> customers) {
    for (Customer c : customers) {
      summary += "\n - " + c.getName() + " (" + format(c.getNumberOfAccounts(), "account") + ")";
    }
  }

  //Make sure correct plural of word is created based on the number passed in:
  //If number passed in is 1 just return the word otherwise add an 's' at the end
  private String format(int number, String word) {
    return number + " " + (number == 1 ? word : word + "s");
  }

  public String getSummary() {
    return summary;
  }
}
