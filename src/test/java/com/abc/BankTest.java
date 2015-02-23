package com.abc;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class BankTest {
    private static final double DOUBLE_DELTA = 1e-15;

    @Test
    public void customerSummary() {
        Bank bank = new Bank();
        Customer john = new Customer("John");
        john.openAccount(new Account(Account.CHECKING));
        bank.addCustomer(john);

        assertEquals("Customer Summary\n - John (1 account)", bank.customerSummary());
    }

    @Test
    public void checkingAccount() {
        Bank bank = new Bank();
        Account checkingAccount = new Account(Account.CHECKING);
        Customer bill = new Customer("Bill").openAccount(checkingAccount);
        bank.addCustomer(bill);

		TimeTuner timeTuner = TimeTuner.mock();
		timeTuner.now("2014-12-01");
        checkingAccount.deposit(100.0);
		timeTuner.now("2015-12-01");
        assertEquals(0.1, bank.totalInterestPaid(), DOUBLE_DELTA);
    }

    @Test
    public void savings_account() {
        Bank bank = new Bank();
        Account checkingAccount = new Account(Account.SAVINGS);
        bank.addCustomer(new Customer("Bill").openAccount(checkingAccount));

		TimeTuner timeTuner = TimeTuner.mock();
		timeTuner.now("2014-12-01");
        checkingAccount.deposit(1500.0);

		timeTuner.now("2015-12-01");
        assertEquals(2.0, bank.totalInterestPaid(), DOUBLE_DELTA);
    }

    @Test
    public void maxi_savings_account() {
        Bank bank = new Bank();
        Account checkingAccount = new Account(Account.MAXI_SAVINGS);
        bank.addCustomer(new Customer("Bill").openAccount(checkingAccount));

		TimeTuner timeTuner = TimeTuner.mock();
		timeTuner.now("2014-12-01");
        checkingAccount.deposit(3000.0);

		timeTuner.now("2015-12-01");
        assertEquals(15.0, bank.totalInterestPaid(), DOUBLE_DELTA);
    }

}
