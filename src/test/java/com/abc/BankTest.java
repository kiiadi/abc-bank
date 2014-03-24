package com.abc;

import junit.framework.Assert;
import org.junit.Test;

import java.math.BigDecimal;

import static com.abc.AccountType.Checking;
import static com.abc.AccountType.MaxiSavings;
import static com.abc.AccountType.Savings;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class BankTest {
    @Test
    public void customerSummary() {
        Bank bank = new Bank();
        Customer john = new Customer("John");
        john.openAccount(new Account(Checking));
        bank.addCustomer(john);

        assertEquals("Customer Summary\n - John (1 account)", bank.customerSummary());
    }

    @Test
    public void checkingAccount() {
        Bank bank = new Bank();
        Account checkingAccount = new Account(Checking);
        Customer bill = new Customer("Bill").openAccount(checkingAccount);
        bank.addCustomer(bill);

        checkingAccount.deposit(new BigDecimal("100.0"));

        assertEquals(new BigDecimal("0.1").compareTo(bank.totalInterestPaid()), 0);
    }

    @Test
    public void savingsAccount() {
        Bank bank = new Bank();
        Account checkingAccount = new Account(Savings);
        bank.addCustomer(new Customer("Bill").openAccount(checkingAccount));

        checkingAccount.deposit(new BigDecimal("1500.0"));

        assertEquals(new BigDecimal("2.0").compareTo(bank.totalInterestPaid()), 0);
    }

    @Test
    public void maxiSavingsAccount() {
        Bank bank = new Bank();
        Account checkingAccount = new Account(MaxiSavings);
        bank.addCustomer(new Customer("Bill").openAccount(checkingAccount));

        checkingAccount.deposit(new BigDecimal("3000.0"));

        assertEquals(new BigDecimal("150.0").compareTo(bank.totalInterestPaid()), 0);
    }

}
