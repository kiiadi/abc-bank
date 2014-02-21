package com.abc;

import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.assertEquals;

public class BankTest {
    private static final double DOUBLE_DELTA = 1e-15;

    @Test
    public void testProduceCustomerSummary() {
        Bank bank = new Bank();
        Customer john = new Customer("John");
        john.openAccount(new Account(AccountType.CHECKING));
        bank.addCustomer(john);

        assertEquals("Customer Summary\n - John (1 account)", bank.customerSummary());
    }

    @Test
    public void testCheckingAccount() {
        Bank bank = new Bank();
        Account checkingAccount = new Account(AccountType.CHECKING);
        Customer bill = new Customer("Bill").openAccount(checkingAccount);
        bank.addCustomer(bill);

		Date transactionDate = DateUtil.getInstance().addDays(new Date(), -1);
        checkingAccount.deposit(1000.0, transactionDate);

        assertEquals(0.0027397260273972603, bank.totalInterestPaid(), DOUBLE_DELTA);
    }

    @Test
    public void testSavingsAccount() {
        Bank bank = new Bank();
        Account checkingAccount = new Account(AccountType.SAVINGS);
        bank.addCustomer(new Customer("Bill").openAccount(checkingAccount));

		Date transactionDate = DateUtil.getInstance().addDays(new Date(), -1);
		checkingAccount.deposit(1500.0, transactionDate);

        assertEquals(0.005479452054794521, bank.totalInterestPaid(), DOUBLE_DELTA);
    }

    @Test
    public void testMaxiSavingsAccount() {
        Bank bank = new Bank();
        Account checkingAccount = new Account(AccountType.MAXI_SAVINGS);
        bank.addCustomer(new Customer("Bill").openAccount(checkingAccount));

   		Date transactionDate = DateUtil.getInstance().addDays(new Date(), -1);
        checkingAccount.deposit(3000.0, transactionDate);
		double testDayInterest = 3000.0 * (5.0 / 100.0 / 365.0);
        assertEquals(testDayInterest, bank.totalInterestPaid(), DOUBLE_DELTA);
    }
}
