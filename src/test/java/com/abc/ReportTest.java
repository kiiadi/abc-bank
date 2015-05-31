package com.abc;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ReportTest {

    private Bank bank;
    private Customer john;

    @Before
    public void init() {
        bank = new Bank();
        john = new Customer("John");
        bank.addCustomer(john);
    }

    @Test
    public void customerSummaryForOneCustomer() {
        john.openAccount(new CheckingAccount());
        Report report = new Report(bank.getCustomers());

        assertEquals("Customer Summary\n - John (1 account)", report.customerSummary());
    }

    @Test
    public void customerSummaryForTwoCustomers() {
        john.openAccount(new CheckingAccount());

        Customer bill = new Customer("Bill");
        bill.openAccount(new CheckingAccount());
        bank.addCustomer(bill);

        Report report = new Report(bank.getCustomers());

        assertEquals("Customer Summary\n"
                        + " - John (1 account)\n"
                        + " - Bill (1 account)",
                report.customerSummary());
    }

    @Test
    public void customerSummaryForOneCustomerTwoAccounts() {
        john.openAccount(new CheckingAccount());
        john.openAccount(new SavingsAccount());

        Report report = new Report(bank.getCustomers());

        assertEquals("Customer Summary\n - John (2 accounts)", report.customerSummary());
    }
}