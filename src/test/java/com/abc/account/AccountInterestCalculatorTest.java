package com.abc.account;

import com.abc.AbstractTestCase;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class AccountInterestCalculatorTest extends AbstractTestCase {

  @Test
  public void checking_interest() {
    AccountInterestCalculator calculator = new AccountInterestCalculator(1000);
    AccountType.Utils.visit(AccountType.CHECKING, calculator);

    assertEquals(1., calculator.getInterest(), DOUBLE_DELTA);
  }

  @Test
  public void savings_interest_lesser_than_1000() {
    AccountInterestCalculator calculator = new AccountInterestCalculator(800);
    AccountType.Utils.visit(AccountType.SAVINGS, calculator);

    assertEquals(0.8, calculator.getInterest(), DOUBLE_DELTA);
  }

  @Test
  public void savings_interest_greater_than_1000() {
    AccountInterestCalculator calculator = new AccountInterestCalculator(1500);
    AccountType.Utils.visit(AccountType.SAVINGS, calculator);

    assertEquals(2., calculator.getInterest(), DOUBLE_DELTA);
  }

  @Test
  public void maxi_savings_interest_no_withdraw_in_the_past_10_Days() {
    AccountInterestCalculator calculator = new AccountInterestCalculator(1000, true);
    AccountType.Utils.visit(AccountType.MAXI_SAVINGS, calculator);

    assertEquals(50., calculator.getInterest(), DOUBLE_DELTA);
  }

  @Test
  public void maxi_savings_interest_withdraw_in_the_past_10_Days() {
    AccountInterestCalculator calculator = new AccountInterestCalculator(1000, false);
    AccountType.Utils.visit(AccountType.MAXI_SAVINGS, calculator);

    assertEquals(10., calculator.getInterest(), DOUBLE_DELTA);
  }
}