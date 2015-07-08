package com.abc;

import com.abc.account.Account;
import com.abc.account.AccountType;
import com.abc.customer.Customer;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class BankTest extends AbstractTestCase {
  @Test
  public void customer_summary() {
    Bank bank = new Bank();
    Customer john = new Customer("John");
    john.openAccount(new Account(AccountType.CHECKING));
    bank.addCustomer(john);

    assertEquals("Customer Summary\n - John (1 account)", bank.customerSummary());
  }

  @Test
  public void total_interest_paid_checking_account() {
    Bank bank = new Bank();

    Customer bill = new Customer("Bill");
    Account checkingAccount = new Account(AccountType.CHECKING);
    bill.openAccount(checkingAccount);
    bank.addCustomer(bill);

    checkingAccount.deposit(100.0);

    assertEquals(0.1, bank.totalInterestPaid(), DOUBLE_DELTA);
  }

  @Test
  public void total_interest_paid_savings_account() {
    Bank bank = new Bank();

    Customer bill = new Customer("Bill");
    Account savingsAccount = new Account(AccountType.SAVINGS);
    bill.openAccount(savingsAccount);
    bank.addCustomer(bill);

    savingsAccount.deposit(1500.0);

    assertEquals(2.0, bank.totalInterestPaid(), DOUBLE_DELTA);
  }

  @Test
  public void total_interest_paid_maxi_savings_account() {
    Bank bank = new Bank();

    Customer bill = new Customer("Bill");
    Account maxiSavingsAccount = new Account(AccountType.MAXI_SAVINGS);
    bill.openAccount(maxiSavingsAccount);
    bank.addCustomer(bill);

    maxiSavingsAccount.deposit(3000.0);

    assertEquals(170.0, bank.totalInterestPaid(), DOUBLE_DELTA);
  }
}
