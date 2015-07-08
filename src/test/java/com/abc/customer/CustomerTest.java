package com.abc.customer;

import com.abc.AbstractTestCase;
import com.abc.account.Account;
import com.abc.account.AccountType;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CustomerTest extends AbstractTestCase {

  @Test
  public void customer_statement_generation() {
    Customer henry = new Customer("Henry");

    Account checkingAccount = new Account(AccountType.CHECKING);
    Account savingsAccount = new Account(AccountType.SAVINGS);

    henry.openAccount(checkingAccount);
    henry.openAccount(savingsAccount);

    checkingAccount.deposit(100.0);
    savingsAccount.deposit(4000.0);
    savingsAccount.withdraw(200.0);

    assertEquals("Statement for Henry\n" +
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
                 "Total In All Accounts $3,900.00", henry.getStatement());
  }

  @Test
  public void account_number_one() {
    Customer oscar = new Customer("Oscar");
    oscar.openAccount(new Account(AccountType.SAVINGS));

    assertEquals(1, oscar.getNumberOfAccounts());
  }

  @Test
  public void account_number_two() {
    Customer oscar = new Customer("Oscar");
    oscar.openAccount(new Account(AccountType.SAVINGS));
    oscar.openAccount(new Account(AccountType.CHECKING));

    assertEquals(2, oscar.getNumberOfAccounts());
  }

  @Test
  public void account_number_three() {
    Customer oscar = new Customer("Oscar");
    oscar.openAccount(new Account(AccountType.SAVINGS));
    oscar.openAccount(new Account(AccountType.CHECKING));
    oscar.openAccount(new Account(AccountType.MAXI_SAVINGS));

    assertEquals(3, oscar.getNumberOfAccounts());
  }

  @Test(expected = IllegalArgumentException.class)
  public void transfer_exception_invalid_from_account() {
    Customer oscar = new Customer("Oscar");

    Account savingsAccount = new Account(AccountType.CHECKING);
    Account maxiSavingsAccount = new Account(AccountType.MAXI_SAVINGS);

    oscar.openAccount(savingsAccount);

    oscar.transfer(100., maxiSavingsAccount, savingsAccount);
  }

  @Test(expected = IllegalArgumentException.class)
  public void transfer_exception_invalid_to_account() {
    Customer oscar = new Customer("Oscar");

    Account savingsAccount = new Account(AccountType.CHECKING);
    Account maxiSavingsAccount = new Account(AccountType.MAXI_SAVINGS);

    oscar.openAccount(savingsAccount);

    oscar.transfer(100., savingsAccount, maxiSavingsAccount);
  }

  @Test
  public void transfer_valid() {
    Customer oscar = new Customer("Oscar");

    Account savingsAccount = new Account(AccountType.CHECKING);
    Account maxiSavingsAccount = new Account(AccountType.MAXI_SAVINGS);

    savingsAccount.deposit(1000.);
    maxiSavingsAccount.deposit(500.);

    oscar.openAccount(savingsAccount);
    oscar.openAccount(maxiSavingsAccount);

    oscar.transfer(500., savingsAccount, maxiSavingsAccount);

    assertEquals(500., savingsAccount.getTotalTransactions(), DOUBLE_DELTA);
    assertEquals(1000., maxiSavingsAccount.getTotalTransactions(), DOUBLE_DELTA);
  }
}
