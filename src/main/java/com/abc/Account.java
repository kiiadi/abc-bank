package com.abc;

import java.math.BigDecimal;


import com.abc.BankConstants.AccountType;
public class Account {
    private final AccountType _accountType;
    private final Integer _id;
    private BigDecimal _amount;
    public Account(Integer id_ , AccountType  accountType_) {
    	_id = id_;
        _accountType = accountType_;
        _amount = BigDecimal.ZERO;
    }
    
    public BigDecimal getAmount() {
    	return _amount;
    }

    public void setAmount( BigDecimal amount_)
    {
    	_amount = amount_;
    }
    public AccountType getAccountType() {
        return _accountType;
    }
	public Integer getId() {
		return _id;
	}
}
