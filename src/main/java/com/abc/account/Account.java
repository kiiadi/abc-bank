package com.abc.account;

import java.math.BigDecimal;
import java.util.Date;

public interface Account extends Lockable {
  public void deposit(double amount);
  public  void withdraw(double amount) throws InSufficientFundsException;
  public void deposit(double amount, Date date);
  public void withdraw(double amount, Date date) throws InSufficientFundsException;
  public BigDecimal interestEarned();
  public AccountType getAccountType();
  public Integer getAccountNumber();
  public BigDecimal getTotalAmount();
  public Boolean transfer(Account to, BigDecimal amount) throws InSufficientFundsException;
}
