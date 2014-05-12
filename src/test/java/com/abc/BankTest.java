package com.abc;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class BankTest {
  private static final double DOUBLE_DELTA = 1e-15;

  @Test
  public void testCustomerSummary() {
    Bank bank = new Bank();

    Customer customer1 = new Customer("Customer1 Name");
    customer1.openAccount(new CheckingAccount());
    bank.addCustomer(customer1);

    Customer customer2 = new Customer("Customer2 Name");
    customer2.openAccount(new SavingsAccount());
    customer2.openAccount(new MaxiSavingsAccount());
    bank.addCustomer(customer2);

    assertEquals("Customer Summary\n" +
                   " - Customer1 Name (1 account)\n" +
                   " - Customer2 Name (2 accounts)", bank.customerSummary());
  }

  @Test
  public void testCheckingAccountInterestPaid() {
    Bank bank = new Bank();
    Account checkingAccount = new CheckingAccount();
    Customer customer = new Customer("Customer Name").openAccount(checkingAccount);
    bank.addCustomer(customer);

    checkingAccount.deposit(100.0);

    assertEquals(0.1, bank.totalInterestPaid(), DOUBLE_DELTA);
  }

  @Test
  public void testSavingsAccountInterestPaid() {
    Bank bank = new Bank();
    Account savingsAccount = new SavingsAccount();
    bank.addCustomer(new Customer("Customer Name").openAccount(savingsAccount));

    savingsAccount.deposit(1500.0);

    assertEquals(2.0, bank.totalInterestPaid(), DOUBLE_DELTA);
  }

  @Test
  public void testMaxiSavingsAccountInterestPaid() {
    Bank bank = new Bank();
    Account maxiSavingsAccount = new MaxiSavingsAccount();
    bank.addCustomer(new Customer("Customer Name").openAccount(maxiSavingsAccount));

    maxiSavingsAccount.deposit(3000.0);

    assertEquals(170.0, bank.totalInterestPaid(), DOUBLE_DELTA);
  }

}
