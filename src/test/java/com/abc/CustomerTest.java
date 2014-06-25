package com.abc;

import org.junit.Ignore;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;

public class CustomerTest {



    @Test() //Test customer statement generation
    public void testApp(){

        Account checkingAccount = new Account(AccountType.CHECKING, 1);
        Account savingsAccount = new Account(AccountType.SAVINGS, 2);

        Customer henry = new Customer("Henry").openAccount(checkingAccount).openAccount(savingsAccount);

        checkingAccount.deposit(BigDecimal.valueOf(100.0));
        savingsAccount.deposit(BigDecimal.valueOf(4000.0));
        savingsAccount.withdraw(BigDecimal.valueOf(200.0));

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
        Customer oscar = new Customer("Oscar").openAccount(new Account(AccountType.SAVINGS, 1));
        assertEquals(1, oscar.getNumberOfAccounts());
    }

    @Test
    public void testTwoAccount(){
        Customer oscar = new Customer("Oscar")
                .openAccount(new Account(AccountType.SAVINGS, 1));
        oscar.openAccount(new Account(AccountType.CHECKING, 2));
        assertEquals(2, oscar.getNumberOfAccounts());
    }

    @Test
    public void testTransferBalance() {
        Account savingsAccount = new Account(AccountType.SAVINGS, 1);
        Account checkingAccount = new Account(AccountType.CHECKING, 2);

        Customer joe = new Customer("Joe");

        joe.openAccount(savingsAccount);
        joe.openAccount(checkingAccount);


        savingsAccount.deposit(BigDecimal.valueOf(100.00));
        checkingAccount.deposit(BigDecimal.valueOf(100.00));

        joe.transferBalance(savingsAccount,checkingAccount, BigDecimal.valueOf(50.00));

        assertEquals(new BigDecimal("50.00"), savingsAccount.getCurrentBalance());
        assertEquals(new BigDecimal("150.00"),checkingAccount.getCurrentBalance());






    }

    @Ignore
    public void testThreeAcounts() {
        Customer oscar = new Customer("Oscar")
                .openAccount(new Account(AccountType.SAVINGS, 1));
        oscar.openAccount(new Account(AccountType.CHECKING, 2));
        assertEquals(3, oscar.getNumberOfAccounts());
    }
}
