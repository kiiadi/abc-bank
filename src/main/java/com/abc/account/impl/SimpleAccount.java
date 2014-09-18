package com.abc.account.impl;

import com.abc.account.AccountType;
import com.abc.account.InSufficientFundsException;
import com.abc.strategy.Strategy;
import com.abc.transaction.Transaction;
import com.abc.transaction.impl.SimpleTransaction;
import com.abc.util.DateProvider;
import com.abc.account.Account;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Created by Archana on 9/14/14.
 */
public class SimpleAccount implements Account {
  /*
    For the use for for rounding the double to Big Decimals.
    I am using the Banker's Rounding i.e. "HALF_EVEN". This is dependent on
    the business use case. For clarity's sake, I am just using this rounding method.
  */
  public final static MathContext mathContext = new MathContext(2, RoundingMode.HALF_EVEN);

  /* SimpelDateFormat is not thread safe. Hence we need to make it ThreadLocal.
     We could make it per account instance of simple date formatter. but that would make it
     unnecessarily costly and unnecessary.
  */
  public final static ThreadLocal<SimpleDateFormat> dateFormatter = new ThreadLocal<SimpleDateFormat>() {
    @Override
    protected SimpleDateFormat initialValue() {
      return new SimpleDateFormat("yyyy-MM-dd");
    }
  };


  private final AccountType accountType;
  private final Integer accountNumber ;

  private static Integer acctNumberGenrator = 1;
  //public Map<Date,Transaction> transactions;
  private List<Transaction> transactions;
  protected BigDecimal totalAmt = new BigDecimal("0.0");
  protected Strategy<List<Transaction>, BigDecimal> interestCalculator;

  protected ReadWriteLock lock = new ReentrantReadWriteLock();
  private Thread lockOwningThread;

  public SimpleAccount(AccountType accountType) {
    this.accountType = accountType;
    //this.transactions = new TreeMap<Date,Transaction>();
    this.transactions = new ArrayList<Transaction>();
    accountNumber = acctNumberGenrator++;
  }

  @Override
  public void deposit(double amount) {
    BigDecimal temp = new BigDecimal(amount);
    validateAmount(amount);
    createAndAddTransaction(temp);
  }

  @Override
  public void deposit(double amount, Date date){
    BigDecimal temp = new BigDecimal(amount);
    validateAmount(amount);
    createAndAddTransactionWithDate(temp, date);
  }

  @Override
  public void withdraw(double amount) throws InSufficientFundsException {
    BigDecimal temp = new BigDecimal(amount);
    validateAmount(amount);
    validateWithdrawalAmount(temp);
    createAndAddTransaction(new BigDecimal(-amount));
  }


  @Override
  public void withdraw(double amount, Date date) throws InSufficientFundsException {
    BigDecimal temp = new BigDecimal(amount);
    validateAmount(amount);
    validateWithdrawalAmount(temp);
    createAndAddTransactionWithDate(new BigDecimal(-amount), date);
  }


  private void validateAmount(double amount) {
    if (amount <= 0) {
      throw new IllegalArgumentException("amount must be greater than zero");

    }
  }

  private void validateWithdrawalAmount(BigDecimal amount) throws InSufficientFundsException{
    if((totalAmt.compareTo((amount)) < 0))
      throw new InSufficientFundsException(totalAmt, amount);
  }

  private void createAndAddTransaction(BigDecimal amount) {
    lock.writeLock().lock();
    totalAmt = totalAmt.add(amount);
    transactions.add(new SimpleTransaction(amount));
    lock.writeLock().unlock();

  }

  private void createAndAddTransactionWithDate(BigDecimal amount, Date date) {
    lock.writeLock().lock();
    totalAmt = totalAmt.add(amount);
    transactions.add(new SimpleTransaction(amount, date));
    lock.writeLock().unlock();
  }

  @Override
  public BigDecimal interestEarned() {
    if(transactions.size() == 0)
      return new BigDecimal("0.0");
    lock.readLock().lock();
    ArrayList<Transaction> t = new ArrayList<Transaction>(transactions);
    lock.readLock().unlock();
    BigDecimal interest = interestCalculator.execute(transactions);
    return interest;
  }

  @Override
  public AccountType getAccountType() {
    return accountType;
  }

  @Override
  public Integer getAccountNumber() {
    return accountNumber;
  }

  @Override
  public BigDecimal getTotalAmount(){
    return totalAmt;
  }


  @Override
  public Boolean transfer(Account to, BigDecimal amount) throws InSufficientFundsException {
    this.lock();
    to.lock();
    this.withdraw(amount.doubleValue());
    to.deposit(amount.doubleValue());
    this.unlock();
    to.unlock();

    return true;
  }


  /**
   * Customers can call this method to acquire the write lock on the Account
   * while doing the transfers so that no other threads can write any other
   * transactions.
   */
  @Override
  public void lock() {
    lockOwningThread = Thread.currentThread();
    lock.writeLock().lock();
  }

  /**
   * Customers when done with the transfer can unlock the write thread once done
   * with the transfer. If the current thread calling this method does not own the
   * lock, then there is some thing wrong. We will throw IllegalMonitorStateException
   */
  @Override
  public void unlock() {
    if(lockOwningThread != Thread.currentThread()){
      throw new IllegalMonitorStateException("The present thread does not own the lock the thread to unlock it.");
    }
    lock.writeLock().unlock();
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof SimpleAccount)) return false;

    SimpleAccount that = (SimpleAccount) o;

    if (accountNumber != null ? !accountNumber.equals(that.accountNumber) : that.accountNumber != null) return false;

    return true;
  }

  @Override
  public int hashCode() {
    return accountNumber != null ? accountNumber.hashCode() : 0;
  }


  public static BigDecimal convertDoubleToBigDecimal(Double amount) {
    return new BigDecimal(amount, mathContext);
  }

  public static Date getFormattedToDate() {
    Date temp = new Date();
    try {
      temp =  dateFormatter.get().parse(DateProvider.INSTANCE.now().toString());
    }catch (ParseException p){
      System.out.println("Failed to Parse Date.");// should never happen.
    }
    return temp;
  }

}
