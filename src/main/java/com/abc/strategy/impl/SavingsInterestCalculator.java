package com.abc.strategy.impl;

import com.abc.account.InterestRate;
import com.abc.transaction.Transaction;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by Archana on 9/14/14.
 */
public class SavingsInterestCalculator<T extends List<Transaction>, V extends BigDecimal> extends AbstractInterestCalculator<T,V>{

  @Override
  protected Double getInterestRate(T t) {
    return InterestRate.SAVINGS.getRate();
  }

}