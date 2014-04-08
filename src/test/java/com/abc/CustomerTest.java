package com.abc;

import org.junit.Test;

import com.abc.accounts.Account;
import com.abc.util.DefaultDateProvider;

import static org.junit.Assert.assertEquals;
import static com.abc.accounts.AccountType.CHECKING;
import static com.abc.accounts.AccountType.SAVINGS;
import static com.abc.accounts.AccountType.MAX_SAVINGS;

public class CustomerTest {

    @Test //Test customer statement generation
    public void testApp(){

        Account checkingAccount = new Account(CHECKING);
        Account savingsAccount = new Account(SAVINGS);

        Customer henry = new Customer("Henry").openAccount(checkingAccount).openAccount(savingsAccount);

        checkingAccount.deposit(100.0, DefaultDateProvider.getInstance().now());
        savingsAccount.deposit(4000.0, DefaultDateProvider.getInstance().now());
        savingsAccount.withdraw(200.0, DefaultDateProvider.getInstance().now());

        assertEquals("Statement for Henry\n" +
                "\n" +
                "Checking Account\n" +
                "  deposit $100.00\n" +
                "Total $100.00\n" +
                "\n" +
                "Savings Account\n" +
                "  deposit $4,000.00\n" +
                "  withdrawal $200.00\n" +
                "Total $3,800.00\n" +
                "\n" +
                "Total In All Accounts $3,900.00", henry.getStatement());
    }

    @Test
    public void testOneAccount(){
        Customer oscar = new Customer("Oscar").openAccount(new Account(SAVINGS));
        assertEquals(1, oscar.getNumberOfAccounts());
    }

    @Test
    public void testTwoAccount(){
        Customer oscar = new Customer("Oscar")
                .openAccount(new Account(SAVINGS));
        oscar.openAccount(new Account(CHECKING));
        assertEquals(2, oscar.getNumberOfAccounts());
    }

    @Test
    public void testThreeAcounts() {
        Customer oscar = new Customer("Oscar")
                .openAccount(new Account(SAVINGS));
        oscar.openAccount(new Account(CHECKING));
        oscar.openAccount(new Account(MAX_SAVINGS));
        assertEquals(3, oscar.getNumberOfAccounts());
    }
}