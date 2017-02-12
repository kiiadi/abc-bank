package com.abc;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class BankTest  extends BaseTestFixture {

    @Test
    public void customerSummary() {
        Bank bank = new Bank();
        Customer john = new Customer("John");
        john.openAccount(Account.newChecking());
        bank.addCustomer(john);

        assertEquals("Customer Summary"+System.lineSeparator()+" - John (1 account)", bank.customerSummary());
    }

    @Test
    public void checkingAccount() {
        Bank bank = new Bank();
        Account checkingAccount = Account.newChecking();
        Customer bill = new Customer("Bill");
        bill.openAccount(checkingAccount);
        bank.addCustomer(bill);

        checkingAccount.deposit(100.0);

        assertEquals(0.1, bank.totalInterestPaid(), DOUBLE_DELTA);
    }

    @Test
    public void savings_account() {
        Bank bank = new Bank();
        Account checkingAccount = Account.newSavings();
        Customer bill = new Customer("Bill");
        bank.addCustomer(bill);
        bill.openAccount(checkingAccount);

        checkingAccount.deposit(1500.0);

        assertEquals(2.0, bank.totalInterestPaid(), DOUBLE_DELTA);
    }

    @Test
    public void maxi_savings_account() {
        Bank bank = new Bank();
        Account checkingAccount = Account.newMaxiSavings();
        Customer bill = new Customer("Bill");
        bank.addCustomer(bill);
        bill.openAccount(checkingAccount);

        checkingAccount.deposit(3000.0);

        assertEquals(170.0, bank.totalInterestPaid(), DOUBLE_DELTA);
    }

}
