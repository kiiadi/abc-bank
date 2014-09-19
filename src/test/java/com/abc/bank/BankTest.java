package com.abc.bank;

import com.abc.account.Account;
import com.abc.account.impl.CheckingAccount;
import com.abc.account.impl.MaxiSavingsAccount;
import com.abc.account.impl.SavingsAccount;
import com.abc.bank.impl.SimpleBank;
import com.abc.customer.Customer;
import com.abc.customer.impl.SimpleCustomer;
import org.junit.Before;
import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import static junit.framework.TestCase.assertTrue;

public class BankTest {
  Bank bank;
  Account account1 ;
  Account account2 ;
  Account account3 ;
  Calendar cal;
  SimpleDateFormat sdf ;

  Date pastDate1;
  Date pastDate2;
  Date pastDate3;

  Customer customer1;
  Customer customer2;

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
    pastDate3 = sdf.parse("10/01/2014");
    customer1 = new SimpleCustomer("Sri");
    customer2 = new SimpleCustomer("Ram");

  }


  @Test
  public void testAddCustomer() throws Exception {
    bank.addCustomer(customer1);
    assertTrue("Bank.addCustomer() not working properly", bank.getFirstCustomer().equals("Sri"));
  }

  @Test
  public void testCustomerSummary() throws Exception {
    bank.addCustomer(customer1);
    bank.addCustomer(customer2);
    customer1.openAccount(account1).openAccount(account2);
    customer2.openAccount(account3);
    account1.deposit(2000);
    account2.deposit(50000);
    String expectedSummary = "Customer Summary\n - Sri (2 accounts)\n - Ram (1 account)";
    assertTrue("Bank.customerSummary() not working properly", expectedSummary.equals(bank.customerSummary()));


  }

  @Test
  public void testTotalInterestPaid() throws Exception {
    bank.addCustomer(customer1);
    bank.addCustomer(customer2);
    customer1.openAccount(account1).openAccount(account2);
    customer2.openAccount(account3);
    account1.deposit(5000, pastDate1);
    account2.deposit(2000, pastDate1);
    account3.deposit(10000, pastDate3);

    double interest = bank.totalInterestPaid();
    assertTrue("Bank.totalInterestPaid() not working properly", interest == 141.6);

  }

  @Test
  public void testGetFirstCustomer() throws Exception {
    bank.addCustomer(customer1);
    bank.addCustomer(customer2);
    customer1.openAccount(account1).openAccount(account2);
    customer2.openAccount(account3);
    account1.deposit(5000, pastDate1);
    account2.deposit(2000, pastDate1);
    account3.deposit(10000, pastDate3);

    assertTrue("Bank.getFirstCustomer() not working properly", bank.getFirstCustomer().equals("Sri"));

  }
}