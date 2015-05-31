package com.abc;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

public class BankTest {

    private Bank bank;
    private Customer john;

    @Before
    public void init() {
        bank = new Bank();
        john = new Customer("John");
    }

    @Test
    public void customerSummaryForOneCustomer() {
        john.openAccount(new CheckingAccount());
        bank.addCustomer(john);
        assertEquals("Customer Summary\n - John (1 account)", bank.customerSummary());
    }

    @Test
    public void customerSummaryForTwoCustomers() {
        john.openAccount(new CheckingAccount());
        bank.addCustomer(john);

        Account checkingAccount = new CheckingAccount();
        Customer bill = new Customer("Bill");
        bill.openAccount(checkingAccount);
        bank.addCustomer(bill);

        assertEquals("Customer Summary\n"
                + " - John (1 account)\n"
                + " - Bill (1 account)",
                bank.customerSummary());
    }

    @Test
    public void customerSummaryForOneCustomerTwoAccounts() {
        john.openAccount(new CheckingAccount());
        john.openAccount(new SavingsAccount());
        bank.addCustomer(john);

        assertEquals("Customer Summary\n - John (2 accounts)", bank.customerSummary());
    }

    @Test
    public void totalInterestPaid() {
        Account checkingAccount = new CheckingAccount();
        john.openAccount(checkingAccount);
        checkingAccount.deposit(100.0);

        Account savingsAccount = new SavingsAccount();
        john.openAccount(savingsAccount);
        savingsAccount.deposit(1500.0);

        Account maxiSavingsAccount = new MaxiSavingsAccount();
        john.openAccount(maxiSavingsAccount);
        maxiSavingsAccount.deposit(3000.0);

        bank.addCustomer(john);

        assertThat(bank.totalInterestPaid(), equalTo(172.1));
    }
}
