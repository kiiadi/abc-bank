package com.abc.test;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;

import org.junit.Ignore;
import org.junit.Test;

import com.abc.Customer;
import com.abc.account.Account;
import com.abc.account.CheckingAccount;
import com.abc.account.SavingsAccount;

public class CustomerTest {

	/**
	 * TODO: Statement should be encapsulated in its own class and use the
	 * Statement instance for assertions. Asserting on formatted String is tedious and error prone.
	 * Also, printing/formatting should be decoupled from the entity representation. Statement is more
	 * of a domain model rather than a print feature.
	 */
    @Test //Test customer statement generation
    public void testApp(){

        Account checkingAccount = new CheckingAccount();
        Account savingsAccount = new SavingsAccount();

        Customer henry = new Customer("Henry").openAccount(checkingAccount).openAccount(savingsAccount);

        checkingAccount.deposit(new BigDecimal("100.00"));
        savingsAccount.deposit(new BigDecimal("4000.00"));
        savingsAccount.withdraw(new BigDecimal("200.00"));
        
        String expectedStatement = "Statement for Henry\n" +
                "\n" +
                "Checking Account\n" +
                "  Deposit $100.00\n" +
                "Total $100.00\n" +
                "\n" +
                "Savings Account\n" +
                "  Deposit $4,000.00\n" +
                "  Withdrawal $200.00\n" +
                "Total $3,800.00\n" +
                "\n" +
                "Total In All Accounts $3,900.00";
        
        assertEquals(expectedStatement, henry.getStatement());
    }

    @Test
    public void testOneAccount(){
        Customer oscar = new Customer("Oscar").openAccount(new SavingsAccount());
        assertEquals(1, oscar.getNumberOfAccounts());
    }

    @Test
    public void testTwoAccount(){
        Customer oscar = new Customer("Oscar")
                .openAccount(new SavingsAccount());
        oscar.openAccount(new CheckingAccount());
        assertEquals(2, oscar.getNumberOfAccounts());
    }

    @Ignore
    public void testThreeAcounts() {
        Customer oscar = new Customer("Oscar")
                .openAccount(new SavingsAccount());
        oscar.openAccount(new CheckingAccount());
        assertEquals(3, oscar.getNumberOfAccounts());
    }
}
