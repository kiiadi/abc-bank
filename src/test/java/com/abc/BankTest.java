package com.abc;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class BankTest {
    private static final double DOUBLE_DELTA = 1e-15;

    @Test
    public void customerSummary() {
        Bank bank = new Bank();
        Customer john = new Customer("John");
        john.openAccount(new CheckingAccount(1002));
        bank.addCustomer(john);

        assertEquals("Customer Summary\n - John (1 account)", bank.customerSummary());
        System.out.println("cust summary" + bank.customerSummary());
    }

    @Test
    public void checkingAccount() {
        Bank bank = new Bank();
        BaseAccount checkingAccount = new CheckingAccount(1001);
        Customer bill = new Customer("Bill").openAccount(checkingAccount);
        bank.addCustomer(bill);

        checkingAccount.deposit(100.0,TransactionType.DEPOSIT);
        System.out.println(bank.totalInterestPaid());

        assertEquals(0.1, bank.totalInterestPaid(), DOUBLE_DELTA);
    }

    @Test
    public void savings_account() {
        Bank bank = new Bank();
        BaseAccount savingsAccount = new SavingsAccount(1003);
        bank.addCustomer(new Customer("Bill").openAccount(savingsAccount));

        savingsAccount.deposit(1500.0,TransactionType.DEPOSIT);
        System.out.println(bank.totalInterestPaid());
        assertEquals(15.0, bank.totalInterestPaid(), DOUBLE_DELTA);
    }

    @Test
    public void maxi_savings_account() {
        Bank bank = new Bank();
        BaseAccount checkingAccount = new MaxiSavings(1004);
        bank.addCustomer(new Customer("Bill").openAccount(checkingAccount));

        checkingAccount.deposit(3000.0,TransactionType.DEPOSIT);
        System.out.println(bank.totalInterestPaid());
        assertEquals(1.5, bank.totalInterestPaid(), DOUBLE_DELTA);
    }

}
