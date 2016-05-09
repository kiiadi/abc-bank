package com.abc;

import org.junit.Test;
import sun.util.resources.cldr.so.CurrencyNames_so;

import java.util.Calendar;
import java.util.Date;

import static org.junit.Assert.assertEquals;

public class BankTest {
    private static final double DOUBLE_DELTA = 1e-15;

    @Test
    public void testCustomerSummary() {
        Bank bank = new Bank();
        Customer john = new Customer("John");
        john.openAccount(Account.CHECKING);
        bank.addCustomer(john);
        assertEquals("Customer Summary\n - John (1 account)", ReportGenerator.generateCustomerSummary(bank));
    }

    @Test
    public void testCheckingAccount() {
        Bank bank = new Bank();
        Customer bill = new Customer("Bill").openAccount(Account.CHECKING);
        bank.addCustomer(bill);
        bill.deposit(100.0, Account.CHECKING);
        bill.getAccountByType(Account.CHECKING).setAccountOpenDate(getAccountOpenDate());
        assertEquals(0.1, bank.calculateTotalInterestPaid(), DOUBLE_DELTA);
    }

    @Test
    public void testSavingsAccount() {
        Bank bank = new Bank();
        Customer bill = new Customer("Bill").openAccount(Account.SAVINGS);
        bank.addCustomer(bill);
        bill.deposit(1500.0, Account.SAVINGS);
        bill.getAccountByType(Account.SAVINGS).setAccountOpenDate(getAccountOpenDate());
        assertEquals(2.0, bank.calculateTotalInterestPaid(), DOUBLE_DELTA);
    }

    @Test
    public void testMaxiSavingsAccount() {
        Bank bank = new Bank();
        Customer bill = new Customer("Bill").openAccount(Account.MAXI_SAVINGS);
        bank.addCustomer(bill);
        bill.deposit(3000.0, Account.MAXI_SAVINGS);
        bill.getAccountByType(Account.MAXI_SAVINGS).setAccountOpenDate(getAccountOpenDate());
        assertEquals(150.0, bank.calculateTotalInterestPaid(), DOUBLE_DELTA);
    }

    @Test
    public void testMultipleCustomerSummary() {
        Bank bank = new Bank();
        Customer frodo = new Customer("Frodo");
        frodo.openAccount(Account.CHECKING);
        frodo.deposit(157.156145040162151156147, Account.CHECKING);
        bank.addCustomer(frodo);
        Customer sam = new Customer("Samwise");
        sam.openAccount(Account.SAVINGS);
        sam.openAccount(Account.MAXI_SAVINGS);
        sam.deposit(265.234, Account.SAVINGS);
        sam.deposit(1000.00, Account.MAXI_SAVINGS);
        bank.addCustomer(sam);
        assertEquals("Customer Summary\n" +
                " - Frodo (1 account)\n" +
                " - Samwise (2 accounts)", ReportGenerator.generateCustomerSummary(bank));
    }

    @Test
    public void testMaxiSavingsAccountWithdrawal() {
        Bank bank = new Bank();
        Customer merry = new Customer("Merry").openAccount(Account.MAXI_SAVINGS);
        bank.addCustomer(merry);
        merry.deposit(3000.0, Account.MAXI_SAVINGS);
        merry.withdraw(1000.0, Account.MAXI_SAVINGS);
        merry.getAccountByType(Account.MAXI_SAVINGS).setAccountOpenDate(getAccountOpenDate());
        assertEquals(2.0, bank.calculateTotalInterestPaid(), DOUBLE_DELTA);
    }

    private Date getAccountOpenDate() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, -365);
        Date accountOpenDate = calendar.getTime();
        return accountOpenDate;
    }
}
