package com.abc;

import org.junit.Test;

import java.util.Calendar;

import static org.junit.Assert.assertEquals;

public class BankTest {
    private static final double DOUBLE_DELTA = 1e-15;

    @Test
    public void customerSummary() {
        Bank bank = new Bank();
        Customer john = new Customer("John");
        john.openAccount(new Account(Account.CHECKING));
        john.openAccount(new Account(Account.SAVINGS));
        bank.addCustomer(john);

        assertEquals("Customer Summary\n - John (2 accounts)", bank.customerSummary());
    }

    @Test
    public void testTwoCustomerSummary() {
        Bank bank = new Bank();
        Customer cust = new Customer("John")
                .openAccount(new Account(Account.CHECKING))
                .openAccount(new Account(Account.SAVINGS));
        bank.addCustomer(cust);

        cust = new Customer("Kyle")
                .openAccount(new Account(Account.CHECKING));
        bank.addCustomer(cust);

        assertEquals("Customer Summary\n - John (2 accounts)\n - Kyle (1 account)", bank.customerSummary());
    }

    @Test
    public void testInterestPaid() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.YEAR, -1);
        Bank bank = new Bank();

        Account checkingsAccount = new Account(Account.CHECKING);
        Account savingsAccount = new Account(Account.SAVINGS);
        Account maxiSavingsAccount = new Account(Account.MAXI_SAVINGS);

        checkingsAccount.setDateInterestAccruedLast(calendar.getTime());
        savingsAccount.setDateInterestAccruedLast(calendar.getTime());
        maxiSavingsAccount.setDateInterestAccruedLast(calendar.getTime());

        Customer john = new Customer("John")
                .openAccount(checkingsAccount)
                .openAccount(savingsAccount);
        bank.addCustomer(john);

        john.deposit(1000.0, checkingsAccount.getAccountNumber());
        john.deposit(2000.0, savingsAccount.getAccountNumber());

        Customer kyle = new Customer("Kyle")
                .openAccount(maxiSavingsAccount);
        bank.addCustomer(kyle);
        kyle.deposit(3000.0, maxiSavingsAccount.getAccountNumber());

        assertEquals(154.00, bank.totalInterestPaid(), 0);

        assertEquals("Statement for John\n" +
                "\n" +
                "Checking Account\n" +
                "  deposit $1,000.00\n" +
                "  deposit $1.00\n" +
                "Total $1,001.00\n" +
                "\n" +
                "Savings Account\n" +
                "  deposit $2,000.00\n" +
                "  deposit $3.00\n" +
                "Total $2,003.00\n" +
                "\n" +
                "Total In All Accounts $3,004.00", john.getStatement());
    }

    @Test
    public void checkingAccount() {
        Bank bank = new Bank();
        Account checkingAccount = new Account(Account.CHECKING);
        Customer bill = new Customer("Bill").openAccount(checkingAccount);
        bank.addCustomer(bill);

        //Test for annual interest
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.YEAR, -1);
        checkingAccount.setDateInterestAccruedLast(calendar.getTime());

        checkingAccount.deposit(100.0);

        assertEquals(0.1, bank.totalInterestPaid(), DOUBLE_DELTA);
    }

    @Test
    public void savings_account() {
        Bank bank = new Bank();
        Account checkingAccount = new Account(Account.SAVINGS);
        bank.addCustomer(new Customer("Bill").openAccount(checkingAccount));

        //Test for 30 day interest
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_MONTH, -30);
        checkingAccount.setDateInterestAccruedLast(calendar.getTime());

        checkingAccount.deposit(1500.0);

        assertEquals(0.1643835616438356, bank.totalInterestPaid(), DOUBLE_DELTA);
    }

    @Test
    public void maxi_savings_account() {
        Bank bank = new Bank();
        Account checkingAccount = new Account(Account.MAXI_SAVINGS);
        bank.addCustomer(new Customer("Bill").openAccount(checkingAccount));

        //Test for 60 day interest
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_MONTH, -60);
        checkingAccount.setDateInterestAccruedLast(calendar.getTime());

        checkingAccount.deposit(3000.0);

        assertEquals(24.65753424657534, bank.totalInterestPaid(), DOUBLE_DELTA);
    }

}
