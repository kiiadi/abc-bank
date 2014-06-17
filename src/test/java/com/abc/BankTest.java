package com.abc;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class BankTest {
    private static final double DOUBLE_DELTA = 1e-10;
    private Bank bank, noCustomerBank;
    private Customer customerJohn;

    @Before
    public void setUp(){
        bank = new Bank();

        customerJohn = bank.addNewCustomer("John", Account.CHECKING);
        customerJohn.openAccount(Account.SAVINGS);
        customerJohn.openAccount(Account.MAXI_SAVINGS);

        noCustomerBank = new Bank();
    }

    @Test
    public void customerSummaryNoCustomers(){
        assertEquals("Customer Summary", noCustomerBank.customerSummary());
    }

    @Test
    public void getFirstCustomerNoCustomers(){
        assertEquals("No Customers", noCustomerBank.getFirstCustomer());
    }

    @Test
    public void addCustomeGetFirstCustomer(){
        assertEquals("John", bank.getFirstCustomer());
    }

    @Test
    public void customerSummaryThreeJohnAccountWordFailure(){
        customerJohn.openAccount(Account.SAVINGS);
        assertNotEquals("Customer Summary\n - John (3 account)", bank.customerSummary());
    }

    @Test
    public void customerSummaryThreeJohnAccounts(){
        customerJohn.openAccount(Account.SAVINGS);
        assertEquals("Customer Summary\n - John (3 accounts)", bank.customerSummary());
    }

    @Test
    public void totalCheckingAccountInterestNoCustomers() {
        assertEquals(0.0, noCustomerBank.totalInterestPaid(), DOUBLE_DELTA);
    }

    @Test
    public void totalCheckingAccountInterest() {
       customerJohn.getAccount(Account.CHECKING).deposit(100.0);
        assertEquals(0.1, bank.totalInterestPaid(), DOUBLE_DELTA);
    }

    @Test
    public void totalSavingsAccountInterest() {
        customerJohn.getAccount(Account.SAVINGS).deposit(1500.0);
        assertEquals(2.0, bank.totalInterestPaid(), DOUBLE_DELTA);
    }

    //Old Test Case that reflects different formula for Maxi Saving Account interest calculation
    @Ignore
    public void maxi_savings_account() {
        customerJohn.getAccount(Account.MAXI_SAVINGS).deposit(3000.0);
        assertEquals(170.0, bank.totalInterestPaid(), DOUBLE_DELTA);
    }

    @Test
    public void MaxiSavingsAccountInterest() {
        customerJohn.getAccount(Account.MAXI_SAVINGS).deposit(3000.0);
        assertEquals(3, bank.totalInterestPaid(), DOUBLE_DELTA);
    }

}
