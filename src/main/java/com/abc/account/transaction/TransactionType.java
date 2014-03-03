/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.abc.account.transaction;

/**
 *
 * @author hiecaxb
 */
public enum TransactionType {
    
    CREDIT("C", "deposit"), 
    DEBIT("D", "withdrawal");
    
    private String code = null;
    private String description = null;
    
    TransactionType(String code, String description) {
        this.code = code;
        this.description = description;
    }

    public String getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }
    
}
