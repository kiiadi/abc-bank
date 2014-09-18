package com.abc.customer;

import com.abc.account.Account;
import com.abc.account.InSufficientFundsException;
import com.abc.account.impl.CheckingAccount;
import com.abc.account.impl.MaxiSavingsAccount;
import com.abc.account.impl.SavingsAccount;
import com.abc.bank.Bank;
import com.abc.bank.impl.SimpleBank;
import com.abc.customer.impl.SimpleCustomer;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import static junit.framework.TestCase.assertTrue;

public class CustomerTest {
  Bank bank;
  Account account1 ;
  Account account2 ;
  Account account3;
  Calendar cal;
  SimpleDateFormat sdf ;

  Date pastDate1;
  Date pastDate2;

  Customer customer;

  @Before
  public void setUp() throws Exception {
    bank = new SimpleBank();
    account1 = new CheckingAccount();
    account2 = new SavingsAccount();
    account3 = new MaxiSavingsAccount();
    cal = Calendar.getInstance();
    sdf = new SimpleDateFormat("dd/MM/yyyy");
    pastDate1 = sdf.parse("21/08/2014");
    pastDate2 = sdf.parse("10/09/2014");

    customer = new SimpleCustomer("Sri");
  }

  @After
  public void tearDown() throws Exception {

  }

  @Test
  public void testGetName() throws Exception {

  }

  @Test
  public void testOpenAccount() throws Exception {
    customer = customer.openAccount(account1).openAccount(account2);
    assertTrue("Customer.openAccount not working properly", customer.getNumberOfAccounts() == 2);

  }

  @Test
  public void testGetNumberOfAccounts() throws Exception {
    customer = customer.openAccount(account1).openAccount(account2).openAccount(account3);
    assertTrue("Customer.openAccount not working properly", customer.getNumberOfAccounts() == 3);
  }

  @Test
  public void testTotalInterestEarned() throws Exception {
    customer = customer.openAccount(account1).openAccount(account2);
    account1.deposit(2000, pastDate1);
    account1.deposit(10000, pastDate2);
    BigDecimal interest = customer.totalInterestEarned();
    assertTrue("Customer.openAccount not working properly", interest.doubleValue() == (double).81);

  }

  @Test
  public void testGetStatement() throws Exception {
    customer = customer.openAccount(account1).openAccount(account2);
    account1.deposit(1000, pastDate1);
    account1.deposit(10000, pastDate2);
    String expected = "Checking Account Number: 7\nTotal:                   $11,000.00";
    String statement = customer.getStatement(account1);
    assertTrue("Customer.getStatement not working properly", expected.equals(statement));
  }

  @Test
  public void testTransfer() throws Exception {
    customer = customer.openAccount(account1).openAccount(account2);
    account1.deposit(5000, pastDate1);
    account2.deposit(2000, pastDate1);

    customer.transfer(account1, account2, new BigDecimal(3000));

    assertTrue("customer.Transfer() not working properly", account1.getTotalAmount().doubleValue() == 2000);
    assertTrue("customer.Transfer() not working properly", account2.getTotalAmount().doubleValue() == 5000);
  }

  @Test(expected = InSufficientFundsException.class)
  public void testTransferForMoreAmount() throws Exception {
    customer = customer.openAccount(account1).openAccount(account2);
    account1.deposit(5000, pastDate1);
    account2.deposit(2000, pastDate1);

    customer.transfer(account1, account2, new BigDecimal(6000));
  }

}