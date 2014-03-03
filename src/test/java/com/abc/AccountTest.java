/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.abc;

import com.abc.account.CheckingAccount;
import com.abc.account.exception.InsufficientFundsException;
import java.math.BigDecimal;
import org.junit.Assert;

import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import static org.junit.Assert.assertTrue;

/**
 *
 * @author hiecaxb
 */
public class AccountTest {
    
    @Test
    public void statement() {
        
        CheckingAccount checkingAccount = new CheckingAccount();
        
        checkingAccount.deposit(new BigDecimal("100.00"));
        
        try {
            checkingAccount.withdraw(new BigDecimal("50.00"));
        } catch (InsufficientFundsException isf) {
            fail("withdrawal issue.");
        }
        
        System.out.println(checkingAccount.statement());
        
        assertEquals("Checking Account\n" +
                " deposit $100.00\n" +
                " withdrawal ($50.00)\n" +
                "Total $50.00" +
                "\n"
                , checkingAccount.statement());
        
    }
    
    @Test
    public void rogueWithdraw() {
        
        boolean withdrawalCheck = false;
                
        CheckingAccount checkingAccount = new CheckingAccount();
        
        try {
            checkingAccount.withdraw(new BigDecimal("-150.00"));
        } catch (InsufficientFundsException isf) {
            fail();
        } catch (Exception e) {
            withdrawalCheck = true;
            assertTrue(e instanceof IllegalArgumentException);
        } finally {
            assertTrue(withdrawalCheck);
        }
        
        withdrawalCheck = false;
        
        try {
            checkingAccount.withdraw(new BigDecimal("150.00"));
        } catch (InsufficientFundsException isf) {
            withdrawalCheck = true;
        } finally {
            assertTrue(withdrawalCheck);
        }
        
        checkingAccount.deposit(new BigDecimal("100.00"));
        
        withdrawalCheck = false;
        try {
            checkingAccount.withdraw(new BigDecimal("150.00"));
        } catch (InsufficientFundsException isf) {
            withdrawalCheck = true;
        } finally {
            assertTrue(withdrawalCheck);
        }
    }
    
   @Test
    public void rogueDeposit() {
        
        boolean depositCheck = false;
        CheckingAccount checkingAccount = new CheckingAccount();
        
        try {
            checkingAccount.deposit(new BigDecimal("-150.00"));
        } catch (Exception e) {
            assertTrue(e instanceof IllegalArgumentException);
            depositCheck = true;
        } finally {
            assertTrue(depositCheck);
        }
    }
    
    @Test
    public void balance() {
        CheckingAccount checkingAccount = new CheckingAccount();
        
        checkingAccount.deposit(new BigDecimal("100.00"));
        
        try {
            checkingAccount.withdraw(new BigDecimal("50.00"));
        } catch (InsufficientFundsException isf) {
            fail("withdrawal issue.");
        }
        
        assertEquals(new BigDecimal("50.00"),checkingAccount.balance());
        
    }
    
}
