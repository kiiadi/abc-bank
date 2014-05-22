package com.abc;

import java.math.BigDecimal;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CustomerTest {

    @Test //Test customer statement generation
    public void testStatement(){

        BigDecimal startingBalance = new BigDecimal("500");
        Account checkingAccount = new CheckingAccount(startingBalance);
        Account savingsAccount = new SavingsAccount(startingBalance);
        Customer henry = new Customer("Henry");
        henry.openAccount(checkingAccount);
        henry.openAccount(savingsAccount);

        checkingAccount.deposit(new BigDecimal("100.0"));
        savingsAccount.withdraw(new BigDecimal("200.0"));

        assertEquals("Statement for Henry\n" +
                "\n" +
                "CheckingAccount\n" +
                "  deposit $500.00\n" +
                "  deposit $100.00\n" +
                "Total $600.00\n" +
                "\n" +
                "SavingsAccount\n" +
                "  deposit $500.00\n" +
                "  withdrawal $200.00\n" +
                "Total $300.00\n" +
                "\n" +
                "Total In All Accounts $900.00", henry.getStatement());
    }

    @Test
    public void testOneAccount(){
        BigDecimal startingBalance = new BigDecimal("500");
        Account savingsAccount = new SavingsAccount(startingBalance);
        Customer oscar = new Customer("Oscar");
        oscar.openAccount(savingsAccount);
        assertEquals(1, oscar.getNumberOfAccounts());
    }

    @Test
    public void testTwoAccount(){
        BigDecimal startingBalance = new BigDecimal("500");
        Account checkingAccount = new CheckingAccount(startingBalance);
        Account savingsAccount = new SavingsAccount(startingBalance);
        Customer oscar = new Customer("Oscar");
        oscar.openAccount(savingsAccount);
        oscar.openAccount(checkingAccount);
        assertEquals(2, oscar.getNumberOfAccounts());
    }

    @Test
    public void testThreeAcounts() {
        BigDecimal startingBalance = new BigDecimal("500");
        Account checkingAccount = new CheckingAccount(startingBalance);
        Account savingsAccount = new SavingsAccount(startingBalance);
        Account maxiSavingsAccount = new MaxiSavingsAccount(startingBalance);
        Customer oscar = new Customer("Oscar");
        oscar.openAccount(savingsAccount);
        oscar.openAccount(checkingAccount);
        oscar.openAccount(maxiSavingsAccount);
        assertEquals(3, oscar.getNumberOfAccounts());
    }
    
    @Test
    public void testCustomerName() {
        Customer oscar = new Customer("Kokou");
        assertEquals("Kokou", oscar.getName());
    }
}
