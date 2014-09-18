package com.abc.account.impl;

import com.abc.account.AccountType;
import com.abc.strategy.impl.CheckingInterestCalculator;
import com.abc.transaction.Transaction;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by Archana on 9/14/14.
 */
public class CheckingAccount extends SimpleAccount{

  public CheckingAccount(){
    super(AccountType.CHECKING);
    interestCalculator = new CheckingInterestCalculator<List<Transaction>, BigDecimal>();
  }
}
