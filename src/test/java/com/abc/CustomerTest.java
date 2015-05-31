package com.abc;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

public class CustomerTest {

    private Customer customer;

    @Before
    public void init() {
        customer = new Customer("Henry");
    }

    @Test
    public void statement() {
        Account checkingAccount = new CheckingAccount();
        Account savingsAccount = new SavingsAccount();
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
        customer.openAccount(new SavingsAccount());
        assertEquals(1, customer.getNumberOfAccounts());
    }

    @Test
    public void openTwoAccounts() {
        customer.openAccount(new SavingsAccount());
        customer.openAccount(new CheckingAccount());
        assertEquals(2, customer.getNumberOfAccounts());
    }

    @Test
    public void openThreeAccounts() {
        customer.openAccount(new SavingsAccount());
        customer.openAccount(new CheckingAccount());
        customer.openAccount(new MaxiSavingsAccount());
        assertEquals(3, customer.getNumberOfAccounts());
    }

    @Test
    public void totalInterestEarned() {
        Account checkingAccount = new CheckingAccount();
        customer.openAccount(checkingAccount);
        checkingAccount.deposit(100.0);

        Account savingsAccount = new SavingsAccount();
        customer.openAccount(savingsAccount);
        savingsAccount.deposit(1500.0);

        assertThat(customer.totalInterestEarned(), equalTo(2.1));
    }
}
