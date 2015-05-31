package com.abc;

import com.abc.account.Account;
import com.abc.account.CheckingAccount;
import com.abc.account.MaxiSavingsAccount;
import com.abc.account.SavingsAccount;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class BankTest {

    private static final double DOUBLE_DELTA = 1.0e-15;

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

        assertEquals(152.1, bank.totalInterestPaid(), DOUBLE_DELTA);
    }
}
