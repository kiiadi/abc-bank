package com.abc;

import org.joda.time.DateTime;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * @author Adam
 *         Date: 5/16/14
 *         Time: 12:10 PM
 */
public class AccountTest {
  DateTime tenDaysAgo = DateTime.now().minusDays(10);
  DateTime tenWeeksAgo = DateTime.now().minusWeeks(10);
  DateTime tenMonthsAgo = DateTime.now().minusMonths(10);

  @Test
  public void testGetLatestTransactionDate() {
    Account testAccount = new Account() {
      @Override String getName() {return "Test Account";}
      @Override double interestEarned() {return 0;}
    };

    testAccount.deposit(100, tenDaysAgo);
    testAccount.deposit(100, tenMonthsAgo);
    testAccount.deposit(100, tenWeeksAgo);

    assertEquals(testAccount.getLatestTransactionDate(), tenDaysAgo);
  }
}
