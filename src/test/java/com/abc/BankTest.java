package com.abc;

import com.abc.accounts.Account;
import com.abc.accounts.CheckingAccount;
import com.abc.accounts.MaxiSavingAccount;
import com.abc.accounts.SavingAccount;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class BankTest {
    private static final double DOUBLE_DELTA = 1e-15;

    @Test
    public void customerSummary() {
        Bank bank = new Bank();
        Customer john = new Customer("John");
        john.openAccount(new CheckingAccount());
        bank.addCustomer(john);

        assertEquals("Customer Summary\n - John (1 account)", bank.customerSummary());
    }

    @Test
    public void shouldReturnCorrectTotalInterestForallCustomers() {
        Bank bank = new Bank();
        Account checkingAccount = new CheckingAccount();
        Customer bill = new Customer("Bill").openAccount(checkingAccount);
        bank.addCustomer(bill);

        checkingAccount.deposit(100.0);

        Account savingAccount = new SavingAccount();
        bank.addCustomer(new Customer("Alex").openAccount(savingAccount));

        savingAccount.deposit(1500.0);

        Account maxiSavingAccount = new MaxiSavingAccount();
        bank.addCustomer(new Customer("Bob").openAccount(maxiSavingAccount));

        maxiSavingAccount.deposit(3000.0);

        assertEquals(172.1, bank.totalInterestPaid(), DOUBLE_DELTA);
    }



}
