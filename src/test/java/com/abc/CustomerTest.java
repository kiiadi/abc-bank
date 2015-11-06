package com.abc;

import org.junit.Ignore;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CustomerTest {

    @Test //Test customer statement generation
    public void testApp(){

        Account checkingAccount = AccountFactory.create(AccountTypes.CHECKING);
        Account savingsAccount = AccountFactory.create(AccountTypes.SAVINGS);

        Customer henry = new Customer("Henry").openAccount(checkingAccount).openAccount(savingsAccount);

        checkingAccount.deposit(100.0);
        savingsAccount.deposit(4000.0);
        savingsAccount.withdraw(200.0);
System.out.println(henry.totalInterestEarned());
        assertEquals(6.7, henry.totalInterestEarned(),DOUBLE_DELTA);
    }
    private static final double DOUBLE_DELTA = 1e-15;
    @Test
    public void testOneAccount(){
        Customer oscar = new Customer("Oscar").openAccount(AccountFactory.create(AccountTypes.SAVINGS));
        assertEquals(1, oscar.getNumberOfAccounts());
    }

    @Test
    public void testTwoAccount(){
        Customer oscar = new Customer("Oscar")
                .openAccount(AccountFactory.create(AccountTypes.SAVINGS));
        oscar.openAccount(AccountFactory.create(AccountTypes.CHECKING));
        assertEquals(2, oscar.getNumberOfAccounts());
    }

    @Ignore
    public void testThreeAcounts() {
        Customer oscar = new Customer("Oscar")
                .openAccount(AccountFactory.create(AccountTypes.SAVINGS));
        oscar.openAccount(AccountFactory.create(AccountTypes.CHECKING));
        assertEquals(3, oscar.getNumberOfAccounts());
    }
}
