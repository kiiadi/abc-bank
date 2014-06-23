package com.abc;

import java.util.Calendar;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class BankTest {
    private static final double DOUBLE_DELTA = 1e-15;

    @Test
    public void customerSummary() {
        Bank bank = new Bank();
        Customer john = new Customer("John");
        john.openAccount(new Account(Account.CHECKING));
        bank.addCustomer(john);

        assertEquals("Customer Summary\n - John (1 account)", bank.customerSummary());
        
        bank.addCustomer(new Customer("Sarah"));
        
        assertEquals("Customer Summary\n - John (1 account)\n - Sarah (0 account)", bank.customerSummary());
        
        john.openAccount(new Account(Account.SAVINGS));
        
        assertEquals("Customer Summary\n - John (2 accounts)\n - Sarah (0 account)", bank.customerSummary());
        
    }

    @Test
    public void checkingAccount() {
        Bank bank = new Bank();
        Account checkingAccount = new Account(Account.CHECKING);
        Customer bill = new Customer("Bill").openAccount(checkingAccount);
        bank.addCustomer(bill);

        Calendar date = Calendar.getInstance();
        date.setTime(DateProvider.getInstance().now());
        date.add(Calendar.DATE, -3);
        
        checkingAccount.deposit(100.0, date.getTime());
        //3 days checking interest
        assertEquals(0.00082191780822, bank.totalInterestPaid(), DOUBLE_DELTA);
    }

    @Test
    public void checkSavingsAccount() {
        Bank bank = new Bank();
        Account account = new Account(Account.SAVINGS);
        bank.addCustomer(new Customer("Bill").openAccount(account));

        Calendar date = Calendar.getInstance();
        date.setTime(DateProvider.getInstance().now());
        date.add(Calendar.DATE, -2);
        
        //2 day interest
        account.deposit(1500.0, date.getTime());
        assertEquals(0.01095890410959, bank.totalInterestPaid(), DOUBLE_DELTA);
    }
    
    @Test
    public void checkSavingsAccountLessThan1000() {
        Bank bank = new Bank();
        Account checkingAccount = new Account(Account.SAVINGS);
        bank.addCustomer(new Customer("Bill").openAccount(checkingAccount));
        
        Calendar date = Calendar.getInstance();
        date.setTime(DateProvider.getInstance().now());
        date.add(Calendar.DATE, -3);

        checkingAccount.deposit(800.0, date.getTime());
        assertEquals(0.0065753424657534, bank.totalInterestPaid(), DOUBLE_DELTA);
    }

    @Test
    public void checkMaxiSavingsAccount() {
        Bank bank = new Bank();
        Account account = new Account(Account.MAXI_SAVINGS);
        bank.addCustomer(new Customer("Bill").openAccount(account));

        Calendar date = Calendar.getInstance();
        date.setTime(DateProvider.getInstance().now());
        date.add(Calendar.DATE, -11);
        account.deposit(3000.0, date.getTime());
    
        // no withdraw transaction within 10 days, interest rate 5%
        assertEquals(4.52054794520548, bank.totalInterestPaid(), DOUBLE_DELTA);
        
        date.setTime(DateProvider.getInstance().now());
        date.add(Calendar.DATE, -2);
        account.withdraw(300, date.getTime());
        // have withdraw transaction within 10 days, interest becomes 0.1%
        assertEquals(0.0887671232876712, bank.totalInterestPaid(), DOUBLE_DELTA);
    }
    
       
    @Test //test get first customer
    public void checkGetFirstCustomer() {
        Bank bank = new Bank();
        assertEquals("Error", bank.getFirstCustomer());
        Account checkingAccount = new Account(Account.MAXI_SAVINGS);
        bank.addCustomer(new Customer("Bill").openAccount(checkingAccount));
        checkingAccount.deposit(1500.0, null);
        assertEquals("Bill", bank.getFirstCustomer());
    }
}
