/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.abc.account;

import java.math.BigDecimal;

/**
 *
 * @author hiecaxb
 */
public class InterestRange {
    
    private BigDecimal interest = null;
    private BigDecimal minimumAmount = null;
    private BigDecimal maximumAmount = null;

    private InterestRange() {}
    
    public InterestRange(BigDecimal interest, BigDecimal minimumAmount, BigDecimal maximumAmount) {
        this.interest = interest;
        this.minimumAmount = minimumAmount;
        this.maximumAmount = maximumAmount;
    }

    public BigDecimal getInterest() {
        return interest;
    }

    public BigDecimal getMinimumAmount() {
        return minimumAmount;
    }

    public BigDecimal getMaximumAmount() {
        return maximumAmount;
    }

    
}
