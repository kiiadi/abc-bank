package com.abc.account;

import com.abc.AbstractTestCase;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class AccountStatementGeneratorTest extends AbstractTestCase {

  @Test
  public void statement_generation() {
    Account account = new Account(AccountType.CHECKING);
    account.deposit(100.0);
    account.deposit(4000.0);
    account.withdraw(200.0);

    AccountStatementGenerator generator = new AccountStatementGenerator(account);

    assertEquals("Checking Account\n" +
                 "  deposit $100.00\n" +
                 "  deposit $4,000.00\n" +
                 "  withdrawal $200.00\n" +
                 "Total $3,900.00", generator.getStatement());
  }
}