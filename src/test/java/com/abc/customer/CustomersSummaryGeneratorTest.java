package com.abc.customer;

import com.abc.AbstractTestCase;
import com.abc.account.Account;
import com.abc.account.AccountType;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;

public class CustomersSummaryGeneratorTest extends AbstractTestCase {

  @Test
  public void customer_statement_generation() {
    Customer henry = new Customer("Henry");

    Account henriCheckingAccount = new Account(AccountType.CHECKING);
    Account henriSavingsAccount = new Account(AccountType.SAVINGS);

    henry.openAccount(henriCheckingAccount);
    henry.openAccount(henriSavingsAccount);

    henriCheckingAccount.deposit(100.0);
    henriSavingsAccount.deposit(4000.0);
    henriSavingsAccount.withdraw(200.0);

    Customer oscar = new Customer("Oscar");

    Account oscarCheckingAccount = new Account(AccountType.CHECKING);
    Account oscarMaxiSavingsAccount = new Account(AccountType.MAXI_SAVINGS);

    oscar.openAccount(oscarCheckingAccount);
    oscar.openAccount(oscarMaxiSavingsAccount);

    oscarCheckingAccount.deposit(1550);
    oscarMaxiSavingsAccount.deposit(4000.0);
    oscarMaxiSavingsAccount.withdraw(200.0);

    CustomersSummaryGenerator generator = new CustomersSummaryGenerator(Arrays.asList(henry, oscar));

    assertEquals("Customer Summary\n" +
                 " - Henry (2 accounts)\n" +
                 " - Oscar (2 accounts)", generator.getSummary());
  }
}