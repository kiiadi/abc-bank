/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.abc;

import com.abc.account.CheckingAccount;
import java.math.BigDecimal;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

/**
 *
 * @author hiecaxb
 */
public class CheckingAccountTest {
    
    @Test
    public void interestEarned() {
        CheckingAccount checkingAccount = new CheckingAccount();
        checkingAccount.deposit(new BigDecimal("1534.00"));
        
        assertEquals(new BigDecimal("1.53"), checkingAccount.interestEarned());
        
        checkingAccount = new CheckingAccount();
        checkingAccount.deposit(new BigDecimal("1536.00"));
        
        assertEquals(new BigDecimal("1.54"), checkingAccount.interestEarned());
    }
    
}
