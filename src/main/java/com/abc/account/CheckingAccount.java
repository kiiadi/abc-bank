/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.abc.account;

import java.math.BigDecimal;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author hiecaxb
 */
public class CheckingAccount extends AbstractAccount {

    private final static List<InterestRange> INTEREST_RANGE_LADDER = new ArrayList<InterestRange>();
    
    static {
        
        InterestRange interestRange = null;
        BigDecimal interestRate = null;
        BigDecimal minimumAmount = null;
        BigDecimal maximumAmount = null;
        
        interestRate = new BigDecimal("0.10");
        minimumAmount = new BigDecimal("0.00");
        maximumAmount = new BigDecimal(Long.MAX_VALUE).setScale(2);
        
        interestRange = new InterestRange(interestRate, minimumAmount, maximumAmount);
        INTEREST_RANGE_LADDER.add(interestRange);
        
    }
    
    public CheckingAccount() {
        super(AccountType.CHECKING, CheckingAccount.INTEREST_RANGE_LADDER);
    }

}
