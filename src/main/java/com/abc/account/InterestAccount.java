/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.abc.account;

import com.abc.account.exception.InsufficientFundsException;
import com.abc.account.transaction.Transaction;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Holds the accrued interest for an account. 
 * @author hiecaxb
 */
public class InterestAccount implements Account {
    
    private List<Transaction> transactions = null;
    
    public InterestAccount() {
        transactions = new ArrayList<Transaction>();
    }

    public void deposit(BigDecimal amount) {
        
        if (amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("A deposit amount can not be zero or negative.");
        }

        Transaction transaction = new Transaction(amount);
        transactions.add(transaction);
    }

    public void withdraw(BigDecimal amount) throws InsufficientFundsException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public BigDecimal interestEarned() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public BigDecimal balance() {
        BigDecimal balance = BigDecimal.ZERO.setScale(2);

        for (Transaction transaction : transactions) {
            balance = balance.add(transaction.getAmount());
        }

        return balance;
    }

    public String statement() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public AccountType getAccountType() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public int getId() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
