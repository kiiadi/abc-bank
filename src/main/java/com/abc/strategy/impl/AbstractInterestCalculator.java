package com.abc.strategy.impl;

import com.abc.account.impl.SimpleAccount;
import com.abc.strategy.Strategy;
import com.abc.transaction.Transaction;
import org.joda.time.DateTime;
import org.joda.time.Days;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * This is the main template which implements a generic interest strategy and defines
 * an algorithm for calculating the Interest for a given account. The sub classes with
 * provide the particular implementation details such as interest and if the interest rate
 * is variable, if so how(in case of Maxi-savings). The calculation algorithm itself is final
 * so that the sub classes don't have the privilege to change that. But are given hooks to
 * provide their particular details that are essential to calculate the interest.
 *
 * Created by Archana on 9/14/14.
 */
public  abstract class AbstractInterestCalculator<T extends List<Transaction>, V extends BigDecimal> implements Strategy<T,V> {

  public static final int DAYS_IN_ONE_YEAR = 365;

  /**
   * Given a set of Transactions, this method will calculate the interest based on daily accrual from
   * the first transaction's date as the starting point. Here is how this is done.
   *
   * 1. Essentially Take First day. For that first date, get all the transactions, sum them up and call this
   *    starting principal. Now Calculate the interest for this starting principal.
   *   1.1 Formula for this Interest calculation is: (principal * (rate / 100)) / 365.
   *
   * 2. This interest is then added to the starting principal. This becomes the principal amount for the next day.
   *
   * 3. From second day onwards, do the following.
   *  3.1 If there are transactions for that day, then sum them up and add this to the  previous principal to make
   *      present principal amount. Calculate Interest for this new amount for the day and add the interest to
   *      the present principal amount to make new principal.
   *  3.2 If there are no transactions, then Calculate Interest for the previous days principal and add the interest
   *      to the previous days principal to make new principal.
   *
   * Repeat this process from day one to today.
   *
   * Note this method is final i.e. the algorithm itself is pretty much static only the interest rate can be
   * changed by the sub classes by implementing the abstract getInterestRate Method;
   * @param t the transactions from an account
   * @return
   */
  @Override
  public final V execute(T t){
    DateTime today = new DateTime(new Date());
    BigDecimal primaryPrincipal;
    //Find FirstDate from which to calculate the inretest from
    DateTime dateInQuestion = new DateTime(findFirstDate(t));
    //FInd the Interest rate
    Double interestRate = getInterestRate(t);
    //find the first day's final amount in the account i.e. principal amount
    primaryPrincipal = calculateTransactionsTotalAmountForGivenDate(t, dateInQuestion.toDate());
    //calculate the interest rate for this principal amount and add it to the principal amount ot get new principal
    primaryPrincipal = primaryPrincipal.add(calculateInterestAmounttForGivenPrincipalAndInterestRate(primaryPrincipal, interestRate));

    //accrue interest for each subsequent day till today... except today
    //increment dateinquestion by one day..
    while((dateInQuestion = dateInQuestion.plusDays(1)).isBefore(today)){
      //find out the total amount deposited for this day... and add it to the principal amount
      // this becomes the new principal. (if the amount is negative, the principal amount is reduced.)
      primaryPrincipal = primaryPrincipal.add(calculateTransactionsTotalAmountForGivenDate(t, dateInQuestion.toDate()));
      //calculate interest for this new principal and add it to itself to get the new principal
      primaryPrincipal = primaryPrincipal.add(calculateInterestAmounttForGivenPrincipalAndInterestRate(primaryPrincipal, interestRate));
    }

    return (V)new BigDecimal(primaryPrincipal.subtract(calculateTotalPrincipalWithoutAcrrual(t))+"", SimpleAccount.mathContext);
  }

  private Date findFirstDate(T transactions) {
    Transaction firstTransaction = transactions.get(0);
    if(firstTransaction != null )
      return firstTransaction.getTransactionDate();
    else
      return null;
  }

  private BigDecimal calculateTransactionsTotalAmountForGivenDate(T transactions, Date date){
    BigDecimal value = new BigDecimal("0.0", SimpleAccount.mathContext);
    for(Transaction trans : transactions){
      if(trans.getTransactionDate().equals(date))
        value = value.add(trans.getAmount());
    }

    return value;
  }

  private BigDecimal calculateInterestAmounttForGivenPrincipalAndInterestRate(BigDecimal amount, Double interestRate){
    return new BigDecimal(amount.doubleValue() * (interestRate / 100) / DAYS_IN_ONE_YEAR, SimpleAccount.mathContext);
  }

 /*
 * Calculates the difference in days between present and transaction date using joda time.
 * @param transaction
 * @return difference in days as int.
 */
  protected int getDifferenceInDays(Transaction transaction){
    final DateTime start = new DateTime(transaction.getTransactionDate().getTime());
    final DateTime end = new DateTime((new Date().getTime()));
    return Days.daysBetween(start, end).getDays();
  }

  private BigDecimal calculateTotalPrincipalWithoutAcrrual(T transactions){
    BigDecimal value = new BigDecimal("0.0", SimpleAccount.mathContext);
    for(Transaction transaction : transactions){
      value = value.add(transaction.getAmount());
    }
    return  value;
  }

  /**
   * Method to be implemented by the sub classes( as in template pattern).
   * @param t
   * @return
   */

  protected abstract Double getInterestRate(T t);

}