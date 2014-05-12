package com.abc;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class BankTest {
  private static final double DOUBLE_DELTA = 1e-15;

  @Test
  public void customerSummary() {
    Bank bank = new Bank();
    Customer customer = new Customer("Customer Name");
    customer.openAccount(new Account(Account.CHECKING));
    bank.addCustomer(customer);

    assertEquals("Customer Summary\n - Customer Name (1 account)", bank.customerSummary());
  }

  @Test
  public void checkingAccount() {
    Bank bank = new Bank();
    Account checkingAccount = new Account(Account.CHECKING);
    Customer customer = new Customer("Customer Name").openAccount(checkingAccount);
    bank.addCustomer(customer);

    checkingAccount.deposit(100.0);

    assertEquals(0.1, bank.totalInterestPaid(), DOUBLE_DELTA);
  }

  @Test
  public void savings_account() {
    Bank bank = new Bank();
    Account checkingAccount = new Account(Account.SAVINGS);
    bank.addCustomer(new Customer("Customer Name").openAccount(checkingAccount));

    checkingAccount.deposit(1500.0);

    assertEquals(2.0, bank.totalInterestPaid(), DOUBLE_DELTA);
  }

  @Test
  public void maxi_savings_account() {
    Bank bank = new Bank();
    Account checkingAccount = new Account(Account.MAXI_SAVINGS);
    bank.addCustomer(new Customer("Customer Name").openAccount(checkingAccount));

    checkingAccount.deposit(3000.0);

    assertEquals(170.0, bank.totalInterestPaid(), DOUBLE_DELTA);
  }

}
