package com.abc;

import java.math.BigDecimal;

import org.junit.Ignore;
import org.junit.Test;

import com.abc.BankConstants.AccountType;
import com.abc.BankConstants.TransactionType;

import static org.junit.Assert.assertEquals;

public class CustomerTest {

    @Test //Test customer statement generation
    public void testApp(){
    	int customerId = 0;
    	int accountId = 0;
    	TransactionManager transactionManager = new TransactionManager(); 
        Account checkingAccount = new Account(++accountId, AccountType.CHECKING);
        Account savingsAccount = new Account(++accountId, AccountType.SAVINGS);

        Customer henry = new Customer(++customerId ,"Henry").openAccount(checkingAccount).openAccount(savingsAccount);
        transactionManager.transaction(henry, checkingAccount, TransactionType.DEPOSIT, new BigDecimal("100.0"));
        transactionManager.transaction(henry, savingsAccount, TransactionType.DEPOSIT, new BigDecimal("4000.0"));
        transactionManager.transaction(henry, savingsAccount, TransactionType.WITHDRAW, new BigDecimal("200.0"));
        
        assertEquals("Statement for Henry\n" +
                "Checking Account\n" +
                "deposit 100.00\n" +
                "Total 100.00\n" +                
                "Savings Account\n" +
                "deposit 4000.00\n" +
                "withdraw 200.00\n" +
                "Total 3800.00\n\n" +
                "Total In All Accounts 3900.00", transactionManager.getStatement(henry));
    }

    @Test
    public void testOneAccount(){
        int customerId = 0;
    	int accountId = 0;
    	Account savingsAccount = new Account(++accountId, AccountType.SAVINGS);
        Customer oscar = new Customer(++customerId ,"Oscar").openAccount(savingsAccount);
        assertEquals(1, oscar.getNumberOfAccounts());
    }

    @Test
    public void testTwoAccount(){
        int customerId = 0;
    	int accountId = 0;
    	Account checkingAccount = new Account(++accountId, AccountType.CHECKING);
    	Account savingsAccount = new Account(++accountId, AccountType.SAVINGS);
        Customer oscar = new Customer(++customerId ,"Oscar").openAccount(savingsAccount);
        oscar.openAccount(checkingAccount);
        assertEquals(2, oscar.getNumberOfAccounts());
    }

    @Ignore
    public void testThreeAcounts() {
        int customerId = 0;
    	int accountId = 0;
    	Account checkingAccount = new Account(++accountId, AccountType.CHECKING);
    	Account savingsAccount = new Account(++accountId, AccountType.SAVINGS);
    	Account maxiSavingAccount = new Account(++accountId, AccountType.MAXI_SAVINGS);
        Customer oscar = new Customer(++customerId ,"Oscar").openAccount(savingsAccount);
        oscar.openAccount(checkingAccount);
        oscar.openAccount(maxiSavingAccount);
        assertEquals(3, oscar.getNumberOfAccounts());
    }
}
