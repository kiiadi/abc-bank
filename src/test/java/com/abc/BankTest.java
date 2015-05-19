package com.abc;

import org.junit.Test;

import java.math.BigDecimal;

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
    public void checkingAccount() {
        Bank bank = new Bank();
        Account checkingAccount = new CheckingAccount();
        Customer bill = new Customer("Bill").openAccount(checkingAccount);
        bank.addCustomer(bill);

        checkingAccount.deposit(BigDecimal.valueOf(100.0));

        assertEquals(0.1, bank.totalInterestPaid().doubleValue(), DOUBLE_DELTA);
    }

    @Test
    public void savings_account() {
        Bank bank = new Bank();
        Account savingAccount = new SavingAccount();
        bank.addCustomer(new Customer("Bill").openAccount(savingAccount));

        savingAccount.deposit(BigDecimal.valueOf(1500.0));

        assertEquals(2.0, bank.totalInterestPaid().doubleValue(), DOUBLE_DELTA);
    }

    @Test
    public void maxi_savings_account() {
        Bank bank = new Bank();
        Account maxiSavingAccount = new MaxiSavingAccount();
        bank.addCustomer(new Customer("Bill").openAccount(maxiSavingAccount));

        maxiSavingAccount.deposit(BigDecimal.valueOf(3000.0));
        assertEquals(150.0, bank.totalInterestPaid().doubleValue(), DOUBLE_DELTA);

        maxiSavingAccount.withdraw(BigDecimal.valueOf(1000.0));
        assertEquals(2.0, bank.totalInterestPaid().doubleValue(), DOUBLE_DELTA);
    }

    @Test(expected = InsufficientBalanceException.class)
    public void insufficientBalance() {
        Bank bank = new Bank();
        Account maxiSavingAccount = new MaxiSavingAccount();
        bank.addCustomer(new Customer("Bill").openAccount(maxiSavingAccount));

        maxiSavingAccount.withdraw(BigDecimal.valueOf(3000.0));
    }

}
