/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abc;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author heyricdkjohn
 */
public class TransferTest {

    public TransferTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     *
     */
    @Test
    public void testTransfer() {

        Account checkingAccount = new Account(Account.CHECKING);
        Account savingsAccount = new Account(Account.SAVINGS);

        checkingAccount.deposit(100.0);
        savingsAccount.deposit(4000.0);

        Customer oscar = new Customer("Oscar")
                .openAccount(savingsAccount);
        oscar.openAccount(checkingAccount);
        System.out.println("NumberOfAccounts ==> " + oscar.getNumberOfAccounts());
        assertEquals(2, oscar.getNumberOfAccounts());

        double testTotal = checkingAccount.sumTransactions() + savingsAccount.sumTransactions();
        double amount = 500.00;
        int from = Account.SAVINGS;
        int to = Account.CHECKING;
        boolean success = false;
        try {
            success = oscar.transferBetweenAccounts(from, to, amount);
        } catch (Exception ex) {
            Logger.getLogger(TransferTest.class.getName()).log(Level.SEVERE, null, ex);
        }

        double transferTotal = checkingAccount.sumTransactions() + savingsAccount.sumTransactions();

        System.out.println("Before Total ==> " + testTotal + "\nAfter Total ==> " + transferTotal);
        double expected = 0.0;
        double actual = (testTotal - transferTotal);
        if (success && expected != actual) {
            success = false;
        }
        System.out.println("Transfer OK ==> " + success);
        assertTrue(success);
    }

}
