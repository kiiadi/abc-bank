package com.abc;

import org.junit.Test;

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
        Bank bank = new Bank();
        Account checkingsAccount = new Account(Account.CHECKING);
        Account savingsAccount = new Account(Account.SAVINGS);
        Account maxiSavingsAccount = new Account(Account.MAXI_SAVINGS);
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

        for (Customer c : bank.getCustomers()) {
            for (Account a : c.getAccounts()) {
                a.interestEarned();
            }
        }

        assertEquals(174.00, bank.totalInterestPaid(), 0);
    }

    @Test
    public void checkingAccount() {
        Bank bank = new Bank();
        Account checkingAccount = new Account(Account.CHECKING);
        Customer bill = new Customer("Bill").openAccount(checkingAccount);
        bank.addCustomer(bill);

        checkingAccount.deposit(100.0);

        assertEquals(0.1, bank.totalInterestPaid(), DOUBLE_DELTA);
    }

    @Test
    public void savings_account() {
        Bank bank = new Bank();
        Account checkingAccount = new Account(Account.SAVINGS);
        bank.addCustomer(new Customer("Bill").openAccount(checkingAccount));

        checkingAccount.deposit(1500.0);

        assertEquals(2.0, bank.totalInterestPaid(), DOUBLE_DELTA);
    }

    @Test
    public void maxi_savings_account() {
        Bank bank = new Bank();
        Account checkingAccount = new Account(Account.MAXI_SAVINGS);
        bank.addCustomer(new Customer("Bill").openAccount(checkingAccount));

        checkingAccount.deposit(3000.0);

        assertEquals(170.0, bank.totalInterestPaid(), DOUBLE_DELTA);
    }

}
