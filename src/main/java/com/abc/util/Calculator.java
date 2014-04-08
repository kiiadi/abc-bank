package com.abc.util;

import java.util.Date;

import com.abc.accounts.AccountBalance;
import com.abc.accounts.AccountType;

public interface Calculator {

	AccountBalance calculate(AccountType accountType, AccountBalance accountBalance, Date date);
}
