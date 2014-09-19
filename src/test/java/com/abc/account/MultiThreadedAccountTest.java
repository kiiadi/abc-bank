package com.abc.account;

import com.abc.account.impl.CheckingAccount;
import com.abc.account.impl.MaxiSavingsAccount;
import com.abc.account.impl.SavingsAccount;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.*;

import static junit.framework.TestCase.assertTrue;

/**
 * Created by Archana on 9/19/14.
 */
public class MultiThreadedAccountTest {

  Account account1 ;
  Account account2 ;
  Account account3;
  Account account4;
  Calendar cal;
  SimpleDateFormat sdf ;

  Date pastDate1;
  Date pastDate2;
  Date pastDate3;

  Date lastNinthDate;

  @Before
  public void setUp() throws Exception {
    account1 = new CheckingAccount();
    account2 = new SavingsAccount();
    account3 = new MaxiSavingsAccount();
    account4 = new MaxiSavingsAccount();
    cal = Calendar.getInstance();
    sdf = new SimpleDateFormat("dd/MM/yyyy");
    pastDate1 = sdf.parse("21/08/2014");
    pastDate2 = sdf.parse("10/09/2014");

    Calendar previousDate = new GregorianCalendar();
    previousDate.set(Calendar.HOUR_OF_DAY, 0);
    previousDate.set(Calendar.MINUTE, 0);
    previousDate.set(Calendar.SECOND, 0);
    previousDate.set(Calendar.MILLISECOND, 0);

    previousDate.add(Calendar.DAY_OF_MONTH, -8);
    lastNinthDate =  previousDate.getTime();

    pastDate3 = sdf.parse("10/09/2014");
  }



  @Test
  public void testEightConcurrentDeposits() throws InterruptedException, ExecutionException {
    AccountWorkUnit deposit = new AccountWorkUnit(account1, new Double(2000), null, TransactionType.DEPOSIT);
    testThreads( 8, deposit);
    assertTrue("* concurrent Deposits not working", account1.getTotalAmount().doubleValue() == 16000);
  }



  @Test
  public void testEightConcurrentWithdrawals() throws InterruptedException, ExecutionException {
    account1.deposit(20000);
    AccountWorkUnit withdraw = new AccountWorkUnit(account1, new Double(2000), null, TransactionType.WITHDRAW);
    testThreads( 8, withdraw);
    assertTrue("* concurrent Withdrawals not working", account1.getTotalAmount().doubleValue() == 4000);
  }


  @Test
  public void test16ConcurrentDepositsAndWithdrawals() throws InterruptedException, ExecutionException {
    account1.deposit(20000);

    AccountWorkUnit deposit = new AccountWorkUnit(account1, new Double(3000), null, TransactionType.DEPOSIT);
    AccountWorkUnit withdraw = new AccountWorkUnit(account1, new Double(2000), null, TransactionType.WITHDRAW);
    testThreads( 8, deposit, withdraw);
    assertTrue("16 concurrent Deposits and Withdrawals not working", account1.getTotalAmount().doubleValue() == 28000);
  }

  @Test
  public void test8ConcurrenTransfers() throws InterruptedException, ExecutionException, InSufficientFundsException {
    account1.deposit(20000);

    AccountWorkUnit transfer = new AccountWorkUnit(account1, new Double(2000), account2, TransactionType.TRANSFER);

    testThreads(8,transfer);
    assertTrue("8 concurrent transfers not working", account1.getTotalAmount().doubleValue() == 4000);
    assertTrue("8 concurrent transfers not working", account2.getTotalAmount().doubleValue() == 16000);

  }

  @Test
  public void test24ConcurrentDepositsAndWithdrawalsAndTransfersOneWay() throws InterruptedException, ExecutionException, InSufficientFundsException {
    account1.deposit(20000);

    AccountWorkUnit deposit = new AccountWorkUnit(account1, new Double(3000), null, TransactionType.DEPOSIT);
    AccountWorkUnit withdraw = new AccountWorkUnit(account1, new Double(2000), null, TransactionType.WITHDRAW);
    AccountWorkUnit transfer = new AccountWorkUnit(account1, new Double(2000), account2, TransactionType.TRANSFER);

    testThreads(8, deposit, withdraw, transfer);
    assertTrue("24 concurrent deposits, withdrawals and transfers one way not working", account1.getTotalAmount().doubleValue() == 12000);
    assertTrue("24 concurrent deposits, withdrawals and transfers one way not working", account2.getTotalAmount().doubleValue() == 16000);

  }

  @Test
  public void test48ConcurrentDepositsAndWithdrawalsAndTransfersTwoWays() throws InterruptedException, ExecutionException, InSufficientFundsException {
    account1.deposit(20000);
    account2.deposit(20000);

    AccountWorkUnit deposit = new AccountWorkUnit(account1, new Double(2000), null, TransactionType.DEPOSIT);
    AccountWorkUnit withdraw = new AccountWorkUnit(account1, new Double(2000), null, TransactionType.WITHDRAW);
    AccountWorkUnit transfer = new AccountWorkUnit(account1, new Double(2000), account2, TransactionType.TRANSFER);

    AccountWorkUnit deposit1 = new AccountWorkUnit(account2, new Double(2000), null, TransactionType.DEPOSIT);
    AccountWorkUnit withdraw1 = new AccountWorkUnit(account2, new Double(2000), null, TransactionType.WITHDRAW);
    AccountWorkUnit transfer1 = new AccountWorkUnit(account2, new Double(2000), account1, TransactionType.TRANSFER);

    testThreads(8, deposit, withdraw, transfer, deposit1, withdraw1, transfer1);
    assertTrue("24 concurrent deposits, withdrawals and transfers two ways not working", account1.getTotalAmount().doubleValue() == 20000);
    assertTrue("24 concurrent deposits, withdrawals and transfers two ways not working", account2.getTotalAmount().doubleValue() == 20000);

  }


  private void testThreads(final int threadCount, Callable<Boolean>... diffTasks) throws InterruptedException, ExecutionException {

    int numDiffTransactions = threadCount * diffTasks.length;
    List<Callable<Boolean>> tasks = new ArrayList<Callable<Boolean>>();
    for(Callable<Boolean> callable :diffTasks){
      tasks.addAll(Collections.nCopies(threadCount, callable));
    }

    ExecutorService executorService = Executors.newFixedThreadPool(numDiffTransactions);
    List<Future<Boolean>> futures = executorService.invokeAll(tasks);
    List<Boolean> resultList = new ArrayList<Boolean>(futures.size());
    // Check for exceptions
    for (Future<Boolean> future : futures) {
      // Throws an exception if an exception was thrown by the task.
      resultList.add(future.get());
    }
  }



  class AccountWorkUnit implements Callable{
    Account presentAccount;
    Double amount ;
    Account toAccount;
    TransactionType transactionType;

    AccountWorkUnit(Account presentAccount, Double amount, Account toAccount, TransactionType transactionType) {
      this.presentAccount = presentAccount;
      this.amount = amount;
      this.toAccount = toAccount;
      this.transactionType = transactionType;
    }

    @Override
    public Boolean call() throws Exception {
      switch(transactionType){
        case DEPOSIT:
          presentAccount.deposit(amount);
          break;
        case WITHDRAW:
          presentAccount.withdraw(amount);
          break;
        case TRANSFER:
          presentAccount.transfer(toAccount, new BigDecimal(amount));
          break;
        case PRINT_SUMMARY:
          presentAccount.getAccountNumber();
          break;
        default:
          break;
      }
      return true;
    }
  }


  private  enum TransactionType {
    DEPOSIT, WITHDRAW, TRANSFER, PRINT_SUMMARY;
  }

}

