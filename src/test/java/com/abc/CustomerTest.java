package com.abc;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CustomerTest {
    private static final double DOUBLE_DELTA = 1e-10;

    private Customer henry;
    private Account checking, savings;


    @Before()
    public void setUp(){
        henry = new Customer("Henry",Account.CHECKING);

        savings = henry.openAccount(Account.SAVINGS);
        checking = henry.getAccount(Account.CHECKING);

    }

    @Test //Test customer statement generation
    public void testApp(){

        checking.deposit(100.0);
        savings.deposit(4000.0);
        savings.withdraw(200.0);

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
        Customer oscar = new Customer("Oscar",Account.SAVINGS);
        assertEquals(1, oscar.getNumberOfAccounts());
    }

    @Test
    public void testTwoAccount(){
        Customer oscar = new Customer("Oscar",Account.SAVINGS);
        oscar.openAccount(Account.CHECKING);
        assertEquals(2, oscar.getNumberOfAccounts());
    }

    @Test(expected = IllegalArgumentException.class)
    public void getAccountFailure(){
        Account account = henry.getAccount(4);
    }

    @Test
    public void getAccountSuccess(){
        assertEquals(henry.getAccount(1),savings);
    }

    @Test(expected = IllegalArgumentException.class)
    public void transferNoSufficientFunds(){
        henry.transfer(checking, savings, 100);
    }

    @Test(expected = IllegalArgumentException.class)
    public void transferIncorrectAmount(){
        henry.transfer(checking, savings, -100);
    }

    @Test
    public void transferSuccess(){
        checking.deposit(1000);
        henry.transfer(checking, savings,100);
        assertEquals(100,savings.getAccountBalance(),DOUBLE_DELTA);
    }

    @Test
    public void transferSuccessCheckTransactions(){
        checking.deposit(1000);
        henry.transfer(checking, savings,100);
        assertEquals("Statement for Henry\n" +
                "\n" +
                "Checking Account\n" +
                "  deposit $1,000.00\n" +
                "  withdrawal $100.00\n" +
                "Total $900.00\n" +
                "\n" +
                "Savings Account\n" +
                "  deposit $100.00\n" +
                "Total $100.00\n" +
                "\n" +
                "Total In All Accounts $1,000.00", henry.getStatement());
    }

    @Ignore
    public void testThreeAcounts() {
        assertEquals(3, henry.getNumberOfAccounts());
    }
}
