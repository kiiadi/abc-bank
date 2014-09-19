package com.abc.customer;

import com.abc.account.Account;
import com.abc.account.InSufficientFundsException;

import java.math.BigDecimal;

/**
 * Created by Archana on 9/14/14.
 */
public interface Customer {
  public String getName();
  public Customer openAccount(Account account);
  public int getNumberOfAccounts();
  public BigDecimal totalInterestEarned();
  public String getStatement(Account account);
  public Boolean transfer(Account from, Account to, BigDecimal amount) throws InSufficientFundsException;
}
