package com.abc;

import org.junit.Ignore;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class BankTest {
    private static final double DOUBLE_DELTA = 1e-15;

    @Ignore
    public void testCustomerSummary() {
        System.out.println("\n===\nTesting - "+new Object(){}.getClass().getEnclosingMethod().getName());
        Bank bank = new Bank();
        Customer john = new Customer("John");
        bank.addCustomer(john);
        Account johnChecking = john.openAccount(Account.CHECKING);
        assertEquals("Customer Summary\n - John (1 account)", bank.customerSummary());
    }

    @Test
    public void testTotalInterestPaidOnAllCheckingAccounts() {
        //note that Marcus has his account one year Serge just opened his account
        System.out.println("\n===\nTesting - "+new Object(){}.getClass().getEnclosingMethod().getName());
        Bank bank = new Bank();
        DateProvider.getInstance().setFutureDate(-365);
        Customer marcus = new Customer("Marcus");
        bank.addCustomer(marcus);
        Account marcusChecking = marcus.openAccount(Account.CHECKING);
        marcus.deposit(marcusChecking.getAccountNumber(),2000.0);

        Customer serge = new Customer("Serge");
        Account sergeChecking = serge.openAccount(Account.CHECKING);
        bank.addCustomer(serge);
        sergeChecking.deposit(1000.0);  // can be done this way too if you have a ref to account.
                                        // in real world customer just has his account number
                                        // so better to do like Marcus deposit i.e. with acc number

        DateProvider.getInstance().reset();
        System.out.println("Total Interest Paid to Marcus: "+marcus.totalInterestEarned());
        System.out.println("Total Interest Paid to Serge: "+serge.totalInterestEarned());
        assertEquals(3.0, bank.totalInterestPaid(), DOUBLE_DELTA);
    }

    @Ignore
    public void testSavingsAccountInterestOnTheSame() {
        System.out.println("\n===\nTesting - "+new Object(){}.getClass().getEnclosingMethod().getName());
        Bank bank = new Bank();
        Customer bill = new Customer("Bill");
        bank.addCustomer(bill);
        Account billSaving = bill.openAccount(Account.SAVINGS);
        billSaving.deposit(1500.0);
        assertEquals(2.0, bank.totalInterestPaid(), DOUBLE_DELTA);
    }

    @Ignore
    public void maxi_savings_account() {
        System.out.println("\n===\nTesting - "+new Object(){}.getClass().getEnclosingMethod().getName());
        Bank bank = new Bank();
        Customer bill = new Customer("Bill");
        bank.addCustomer(bill);
        Account billMaxi = bill.openAccount(Account.MAXI_SAVINGS);
        billMaxi.deposit(3000.0);
        assertEquals(170.0, bank.totalInterestPaid(), DOUBLE_DELTA);
    }

}