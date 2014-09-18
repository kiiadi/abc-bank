package com.abc.customer.impl;

import com.abc.account.Account;
import com.abc.account.InSufficientFundsException;
import com.abc.account.impl.SimpleAccount;
import com.abc.customer.Customer;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

import static java.lang.Math.abs;

/**
 * Created by Archana on 9/14/14.
 */
public class SimpleCustomer implements Customer {
  private String name;
  private Set<Account> accounts;

  public SimpleCustomer(String name) {
    this.name = name;
    this.accounts = new HashSet<Account>();
  }

  @Override
  public String getName() {
    return name;
  }

  @Override
  public Customer openAccount(Account account) {
    accounts.add(account);
    return this;
  }


  @Override
  public int getNumberOfAccounts() {
    return accounts.size();
  }

  @Override
  public BigDecimal totalInterestEarned() {
    Double total = new Double(0);
    for (Account a : accounts)
      total +=  a.interestEarned().doubleValue();
    return SimpleAccount.convertDoubleToBigDecimal(total);
  }

  @Override
  public String getStatement(Account a) {
    String s = "";

    //Translate to pretty account type
    switch(a.getAccountType()){
      case CHECKING:
        s += "Checking Account Number: "+a.getAccountNumber();
        break;
      case SAVINGS:
        s += "Savings Number: \n"+a.getAccountNumber();
        break;
      case MAXI_SAVINGS:
        s += "Maxi Savings Account Number:\n"+a.getAccountNumber();
        break;
    }

    //Now total up all the transactions
    BigDecimal total = a.getTotalAmount();

    s += "\nTotal:                   " + toDollars(total.doubleValue());
    return s;
  }

  private String toDollars(double d){
    return String.format("$%,.2f", abs(d));
  }

  @Override
  public Boolean transfer(Account from, Account to, BigDecimal amount) throws InSufficientFundsException {
    if(accounts.contains(from) && accounts.contains(to)){
      from.lock();
      to.lock();
      from.withdraw(amount.doubleValue());
      to.deposit(amount.doubleValue());
      from.unlock();
      to.unlock();
      return true;
    }
    return false;
  }

}
