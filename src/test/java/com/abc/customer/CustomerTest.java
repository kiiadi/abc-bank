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
}
