package com.abc.strategy.impl;

import com.abc.account.InterestRate;
import com.abc.transaction.Transaction;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by Archana on 9/14/14.
 */
public class MaxiSavingsInterestCalculator<T extends List<Transaction>, V extends BigDecimal> extends AbstractInterestCalculator<T,V>{

  @Override
  protected Double getInterestRate(T t) {
    if(hasTransactionInLastTendays(t))
      return InterestRate.MAXI_SAVINGS_MIN.getRate();
    else
      return InterestRate.MAXI_SAVINGS_MAX.getRate();
  }
  private boolean hasTransactionInLastTendays(T transactions){
    for(Transaction transaction : transactions) {
      int daysDifference = getDifferenceInDays(transaction);
      if(daysDifference <= 10)
        return true;
    }
    return false;
  }

}