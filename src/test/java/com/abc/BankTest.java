package com.abc;

import org.junit.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;

import static org.junit.Assert.assertEquals;

public class BankTest {
    private static final double DOUBLE_DELTA = 1e-15;

    @Test
    public void customerSummary() {
        Bank bank = new Bank();
        Customer john = new Customer("John");
        john.openAccount( Account.getAccount(Account.CHECKING));
        bank.addCustomer(john);

        assertEquals("Customer Summary\n - John (1 account)", bank.customerSummary());
    }

    @Test
    public void checkingAccount() {
        Bank bank = new Bank();
        Account checkingAccount = Account.getAccount(Account.CHECKING);
        Customer bill = new Customer("Bill").openAccount(checkingAccount);
        bank.addCustomer(bill);

        checkingAccount.deposit(new BigDecimal(100.0));

        assertEquals(new BigDecimal(0.1).setScale(8, RoundingMode.HALF_UP), bank.totalInterestPaid().setScale(8,RoundingMode.HALF_UP));
    }

    @Test
    public void savings_account() {
        Bank bank = new Bank();
        Account checkingAccount =  Account.getAccount(Account.SAVINGS);
        bank.addCustomer(new Customer("Bill").openAccount(checkingAccount));

        checkingAccount.deposit(new BigDecimal(1500.0));

        assertEquals(new BigDecimal(2.0).setScale(8, RoundingMode.HALF_UP), bank.totalInterestPaid().setScale(8, RoundingMode.HALF_UP));
    }

    @Test
    public void maxi_savings_account() {
        Bank bank = new Bank();
        Account checkingAccount =  Account.getAccount(Account.MAXI_SAVINGS);
        bank.addCustomer(new Customer("Bill").openAccount(checkingAccount));

        checkingAccount.deposit(new BigDecimal(3000.0));

        assertEquals(new BigDecimal(170.0).setScale(8, RoundingMode.HALF_UP), bank.totalInterestPaid().setScale(8, RoundingMode.HALF_UP));
    }

}
