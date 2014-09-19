package com.abc.bank;

import com.abc.customer.Customer;

/**
 * Created by Archana on 9/18/14.
 */
public interface Bank {
  void addCustomer(Customer customer);

  String customerSummary();

  double totalInterestPaid();

  String getFirstCustomer();
}
