/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.abc.account;

/**
 *
 * @author hiecaxb
 */
public enum AccountType {
    
    CHECKING("Checking Account"), 
    SAVINGS("Savings Account"), 
    MAXI_SAVINGS("Maxi Savings Account");
    
    private String description = null;
    
    AccountType(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
    
}
