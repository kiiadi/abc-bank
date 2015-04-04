package com.abc;

import org.junit.Test;

import com.abc.BankConstants.AccountType;

import static org.junit.Assert.assertEquals;

public class BankTest {
    @Test
    public void testCustomerSummary() {
        Bank bank = new Bank();
        Customer john = new Customer("John");
        john.openAccount(new Account(AccountType.CHECKING));
        bank.addCustomer(john);
        assertEquals("Customer Summary\n - John (1 account)", bank.customerSummary());
    }

    @Test
    public void testCheckingAccount() {
        Bank bank = new Bank();
        String customerName = "Bill";
        Account checkingAccount = new Account(AccountType.CHECKING);
        Customer bill = new Customer(customerName).openAccount(checkingAccount);
        assertEquals(customerName, checkingAccount.getCustomerName());
        bank.addCustomer(bill);
        checkingAccount.deposit(36500.0);
        checkingAccount.applyInterest();        
        assertEquals(0.1, bank.totalInterestPaid(), BankTestConstants.DOUBLE_DELTA);
    }

    @Test
    public void testSavingsAccount() {
        Bank bank = new Bank();
        Account checkingAccount = new Account(AccountType.SAVINGS);
        bank.addCustomer(new Customer("Bill").openAccount(checkingAccount));
        checkingAccount.deposit(183000.0);
        checkingAccount.applyInterest();
        assertEquals(1, bank.totalInterestPaid(), BankTestConstants.DOUBLE_DELTA);
    }

    @Test
    public void testMaxiSavingsAccount() {
        Bank bank = new Bank();
        Account checkingAccount = new Account(AccountType.MAXI_SAVINGS);
        bank.addCustomer(new Customer("Bill").openAccount(checkingAccount));

        checkingAccount.deposit(36500.0);
        checkingAccount.applyInterest();

        assertEquals(0.1, bank.totalInterestPaid(), BankTestConstants.DOUBLE_DELTA);
    }

}
