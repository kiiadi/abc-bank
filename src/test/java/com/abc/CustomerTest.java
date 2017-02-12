package com.abc;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CustomerTest extends  BaseTestFixture {

    @Test //Test customer statement generation
    public void testApp() {

        Account checkingAccount = Account.newChecking();
        Account savingsAccount = Account.newSavings();

        Customer henry = new Customer("Henry");
        henry.openAccount(checkingAccount);
        henry.openAccount(savingsAccount);

        checkingAccount.deposit(100.0);
        savingsAccount.deposit(4000.0);
        savingsAccount.withdraw(200.0);

        assertEquals("Statement for Henry" +
                System.lineSeparator() + System.lineSeparator() +
                "Checking Account" + System.lineSeparator() +
                "  deposit $100.00" + System.lineSeparator() +
                "Total $100.00" +
                System.lineSeparator() + System.lineSeparator() +
                "Savings Account" + System.lineSeparator() +
                "  deposit $4,000.00" + System.lineSeparator() +
                "  withdrawal $200.00" + System.lineSeparator() +
                "Total $3,800.00" +
                System.lineSeparator() + System.lineSeparator() +
                "Total In All Accounts $3,900.00", henry.getStatement());
    }

    @Test
    public void testOneAccount() {
        Customer oscar = new Customer("Oscar");
        oscar.openAccount(Account.newSavings());
        assertEquals(1, oscar.getNumberOfAccounts());
    }

    @Test
    public void testTwoAccount() {
        Customer oscar = new Customer("Oscar");
        oscar.openAccount(Account.newSavings());
        oscar.openAccount(Account.newChecking());
        assertEquals(2, oscar.getNumberOfAccounts());
    }

    @Ignore
    public void testThreeAcounts() {
        Customer oscar = new Customer("Oscar");
        oscar.openAccount(Account.newSavings());
        oscar.openAccount(Account.newChecking());
        assertEquals(3, oscar.getNumberOfAccounts());
    }

    @Test
    public void testTransfer(){
        Customer oscar = new Customer("Oscar");
        Account c1 = Account.newChecking() ;
        Account c2 = Account.newChecking() ;
        oscar.openAccount(c1);
        oscar.openAccount(c2);
        c1.deposit(1111);
        c1.deposit(222);
        c1.withdraw(333);
        c2.deposit(3000);
        oscar.transfer(c2,c1,1000);
        assertEquals(2000,c1.sumTransactions(),DOUBLE_DELTA);
        assertEquals(2000,c2.sumTransactions(),DOUBLE_DELTA);
    }
}
