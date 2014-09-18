package com.abc.account;

import com.abc.account.impl.CheckingAccount;
import com.abc.account.impl.MaxiSavingsAccount;
import com.abc.account.impl.SavingsAccount;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import static junit.framework.TestCase.assertTrue;

public class AccountTest {

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

  @After
  public void tearDown() throws Exception {

  }

  @Test
  public void testDeposit() throws Exception {
    account1.deposit(1000);
    account1.deposit(2000);

    assertTrue("Account.deposit(). not working properly", account1.getTotalAmount().doubleValue() == 3000);
  }

  @Test
  public void testWithdraw() throws Exception {
    account1.deposit(1000);
    account1.withdraw(500);

    assertTrue("Account.withdraw(). not working properly", account1.getTotalAmount().doubleValue() == 500);
  }


  @Test(expected = InSufficientFundsException.class)
  public void testWithdrawMore() throws Exception {
    account1.deposit(1000);
    account1.withdraw(1500);

    System.out.println(account1.getTotalAmount().doubleValue());

  }

  @Test
  public void testDeposit1() throws Exception {
    account1.deposit(1000, pastDate1);
    account1.deposit(4000, pastDate2);

    assertTrue("Account.deposit(date). not working properly", account1.getTotalAmount().doubleValue() == 5000);

  }

  @Test
  public void testWithdraw1() throws Exception {
    account1.deposit(4000, pastDate1);
    account1.withdraw(500, pastDate2);

    assertTrue("Account.withdraw(date). not working properly", account1.getTotalAmount().doubleValue() == 3500);
  }

  @Test
  public void testInterestEarned() throws Exception {
    account1.deposit(1000, pastDate1);
    account1.deposit(4000, pastDate2);

    BigDecimal interest = account1.interestEarned();
    assertTrue("Account.InterestEarned() not working properly", interest.doubleValue() == (double).35);
  }

  @Test
  public void testGetAccountType() throws Exception {
    assertTrue("Account.getAccountType() not working properly", account1.getAccountType() == AccountType.CHECKING);
  }

  @Test
  public void testGetTotalAmount() throws Exception {
    account1.deposit(1000, pastDate1);
    account1.deposit(4000, pastDate2);

    assertTrue("Account.getTotalAmount() not working properly", account1.getTotalAmount().doubleValue() == 5000);

  }

  @Test
  public void testTransfer() throws Exception {
    account1.deposit(5000, pastDate1);
    account2.deposit(2000, pastDate1);

    account1.transfer(account2, new BigDecimal(3000));

    assertTrue("Account.Transfer not working properly", account1.getTotalAmount().doubleValue() == 2000);
    assertTrue("Account.Transfer not working properly", account2.getTotalAmount().doubleValue() == 5000);
  }

  @Test(expected = InSufficientFundsException.class)
  public void testTransferForMoreAmount() throws Exception {
    account1.deposit(5000, pastDate1);
    account2.deposit(2000, pastDate1);

    account1.transfer(account2, new BigDecimal(6000));
  }
  @Test
  public void testMaxiSavingsWithOutWithdrawal(){
    account3.deposit(5000, pastDate1);
    account3.deposit(2000, pastDate1);

    BigDecimal interest = account3.interestEarned();
    assertTrue("Account.InterestEarned() not working properly", interest.doubleValue() == 11);
  }

  @Test
  public void testMaxiSavingsWithWithdrawal() throws Exception{
    account3.deposit(5000, pastDate1);
    account3.deposit(4000, pastDate1);
    account3.withdraw(2000, lastNinthDate);
    BigDecimal interest = account3.interestEarned();
    assertTrue("Account.InterestEarned() not working properly", interest.doubleValue() == .67);
  }

}