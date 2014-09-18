package com.abc.account.impl;

import com.abc.account.AccountType;
import com.abc.strategy.impl.MaxiSavingsInterestCalculator;
import com.abc.transaction.Transaction;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by Archana on 9/14/14.
 */
public class MaxiSavingsAccount extends SimpleAccount {
  public MaxiSavingsAccount(){
    super(AccountType.MAXI_SAVINGS);
    interestCalculator = new MaxiSavingsInterestCalculator<List<Transaction>, BigDecimal>();
  }

}
