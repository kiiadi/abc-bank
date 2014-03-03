/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.abc;

import com.abc.account.SavingsAccount;
import java.math.BigDecimal;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

/**
 *
 * @author hiecaxb
 */
public class SavingsAccountTest {

    @Test
    public void interestEarned() {
        
        long _longInterest;
        BigDecimal interest = null;
        
        SavingsAccount savingsAccount = new SavingsAccount();
        savingsAccount.deposit(new BigDecimal("3000.00"));
        
        // ----
        _longInterest = 1 + 4;
        interest = new BigDecimal(_longInterest);
        interest = interest.setScale(2);
        
        assertEquals(interest, savingsAccount.interestEarned());
        
        // ----
        savingsAccount = new SavingsAccount();
        savingsAccount.deposit(new BigDecimal("1000.00"));
        
        assertEquals(new BigDecimal("1.00"), savingsAccount.interestEarned());

        // ----
        savingsAccount = new SavingsAccount();
        savingsAccount.deposit(new BigDecimal("888.00"));
        
        assertEquals(new BigDecimal("0.89"), savingsAccount.interestEarned());        
    }
}
