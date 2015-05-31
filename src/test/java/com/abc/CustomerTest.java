package com.abc;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CustomerTest {

    private Customer customer;

    @Before
    public void init() {
        customer = new Customer("Henry");
    }

    @Test
    public void statement() {
        Account checkingAccount = new Account(Account.CHECKING);
        Account savingsAccount = new Account(Account.SAVINGS);
        customer.openAccount(checkingAccount);
        customer.openAccount(savingsAccount);

        checkingAccount.deposit(100.0);
        savingsAccount.deposit(4000.0);
        savingsAccount.withdraw(200.0);

        assertEquals("Statement for Henry\n" +
                '\n' +
                "Checking Account\n" +
                "  deposit $100.00\n" +
                "Total $100.00\n" +
                '\n' +
                "Savings Account\n" +
                "  deposit $4,000.00\n" +
                "  withdrawal $200.00\n" +
                "Total $3,800.00\n" +
                '\n' +
                "Total In All Accounts $3,900.00", customer.getStatement());
    }

    @Test
    public void openOneAccount(){
        customer.openAccount(new Account(Account.SAVINGS));
        assertEquals(1, customer.getNumberOfAccounts());
    }

    @Test
    public void openTwoAccounts() {
        customer.openAccount(new Account(Account.SAVINGS));
        customer.openAccount(new Account(Account.CHECKING));
        assertEquals(2, customer.getNumberOfAccounts());
    }

    @Test
    public void openThreeAccounts() {
        customer.openAccount(new Account(Account.SAVINGS));
        customer.openAccount(new Account(Account.CHECKING));
        customer.openAccount(new Account(Account.MAXI_SAVINGS));
        assertEquals(3, customer.getNumberOfAccounts());
    }
}
