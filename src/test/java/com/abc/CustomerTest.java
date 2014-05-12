package com.abc;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CustomerTest {

  @Test
  public void testStatement() {

    Account checkingAccount = new CheckingAccount();
    Account savingsAccount = new SavingsAccount();

    Customer customer = new Customer("Customer Name").openAccount(checkingAccount).openAccount(savingsAccount);

    checkingAccount.deposit(100.0);
    savingsAccount.deposit(4000.0);
    savingsAccount.withdraw(200.0);

    assertEquals("Statement for Customer Name\n" +
                   "\n" +
                   "Checking Account\n" +
                   "  deposit $100.00\n" +
                   "Total $100.00\n" +
                   "\n" +
                   "Savings Account\n" +
                   "  deposit $4,000.00\n" +
                   "  withdrawal $200.00\n" +
                   "Total $3,800.00\n" +
                   "\n" +
                   "Total In All Accounts $3,900.00", customer.getStatement());
  }

  @Test
  public void testNegativeStatement() {

    Account checkingAccount = new CheckingAccount();
    Account savingsAccount = new SavingsAccount();

    Customer customer = new Customer("Customer Name").openAccount(checkingAccount).openAccount(savingsAccount);

    checkingAccount.deposit(100.0);
    savingsAccount.deposit(400.0);
    savingsAccount.withdraw(2000.0);

    assertEquals("Statement for Customer Name\n" +
                   "\n" +
                   "Checking Account\n" +
                   "  deposit $100.00\n" +
                   "Total $100.00\n" +
                   "\n" +
                   "Savings Account\n" +
                   "  deposit $400.00\n" +
                   "  withdrawal $2,000.00\n" +
                   "Total -$1,600.00\n" +
                   "\n" +
                   "Total In All Accounts -$1,500.00", customer.getStatement());
  }

  @Test
  public void testOneAccount() {
    Customer customer = new Customer("Customer Name").openAccount(new SavingsAccount());
    assertEquals(1, customer.getNumberOfAccounts());
  }

  @Test
  public void testTwoAccounts() {
    Customer customer = new Customer("Customer Name");
    customer.openAccount(new SavingsAccount());
    customer.openAccount(new CheckingAccount());
    assertEquals(2, customer.getNumberOfAccounts());
  }

  @Test
  public void testThreeAccounts() {
    Customer customer = new Customer("Customer Name");
    customer.openAccount(new SavingsAccount());
    customer.openAccount(new CheckingAccount());
    customer.openAccount(new MaxiSavingsAccount());
    assertEquals(3, customer.getNumberOfAccounts());
  }
}
