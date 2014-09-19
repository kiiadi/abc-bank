package com.abc.account.impl;

import com.abc.account.AccountType;
import com.abc.strategy.impl.SavingsInterestCalculator;
import com.abc.transaction.Transaction;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by Archana on 9/14/14.
 */
public class SavingsAccount extends SimpleAccount {
  public SavingsAccount() {
    super(AccountType.SAVINGS);
    interestCalculator  = new SavingsInterestCalculator<List<Transaction>, BigDecimal>();
  }
}
