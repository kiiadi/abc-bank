package com.abc;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

public class BankTest {

    @Test
    public void totalInterestPaid() {
        Customer john = new Customer("John");
        Account checkingAccount = new CheckingAccount();
        john.openAccount(checkingAccount);
        checkingAccount.deposit(100.0);

        Account savingsAccount = new SavingsAccount();
        john.openAccount(savingsAccount);
        savingsAccount.deposit(1500.0);

        Account maxiSavingsAccount = new MaxiSavingsAccount();
        john.openAccount(maxiSavingsAccount);
        maxiSavingsAccount.deposit(3000.0);

        Bank bank = new Bank();
        bank.addCustomer(john);

        assertThat(bank.totalInterestPaid(), equalTo(172.1));
    }
}
