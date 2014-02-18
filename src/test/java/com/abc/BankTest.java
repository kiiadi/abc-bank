package com.abc;

import org.junit.Ignore;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class BankTest {
    private static final double DOUBLE_DELTA = 1e-15;

    @Test
    public void testCustomerSummary() {
        System.out.println("\n===\nTesting - "+new Object(){}.getClass().getEnclosingMethod().getName());
        Bank bank = new Bank();
        Customer john = new Customer("John");
        bank.addCustomer(john);
        Account johnChecking = john.openAccount(Account.CHECKING);
        assertEquals("Customer Summary\n - John (1 account)", bank.customerSummary());
    }

    @Test
    public void testTotalInterestPaidOnAllCheckingAccountsFor12Month() {
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

    @Test
    public void testTotalInterestPaidByTheBankOnAllAccounts() {
        System.out.println("\n===\nTesting - "+new Object(){}.getClass().getEnclosingMethod().getName());
        Bank bank = new Bank();
        DateProvider.getInstance().setFutureDate(-365);
        Customer c1 = new Customer("Customer1");
        bank.addCustomer(c1);
        Account c1Checking = c1.openAccount(Account.CHECKING);
        c1.deposit(c1Checking.getAccountNumber(),2000.0);

        Customer c2 = new Customer("Customer2");
        bank.addCustomer(c2);
        Account c2Savings = c2.openAccount(Account.SAVINGS);
        c2.deposit(c2Savings.getAccountNumber(),3000.0);

        Customer c3 = new Customer("Customer3");
        bank.addCustomer(c3);
        Account c3Maxi = c3.openAccount(Account.MAXI_SAVINGS);
        c3.deposit(c3Maxi.getAccountNumber(),10000.0);

        DateProvider.getInstance().reset();
        assertEquals(507.0, bank.totalInterestPaid(), DOUBLE_DELTA);
    }

}