package com.abc.account;

import com.abc.account.exception.InsufficientFundsException;
import java.math.BigDecimal;

public interface Account {
   
    public void deposit(BigDecimal amount);

    public void withdraw(BigDecimal amount) throws InsufficientFundsException;

    public BigDecimal interestEarned();

    public BigDecimal balance();
    
    public String statement();
    
    public AccountType getAccountType();
    
    public int getId();

}