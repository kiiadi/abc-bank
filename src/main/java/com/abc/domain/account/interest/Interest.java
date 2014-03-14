package com.abc.domain.account.interest;

import com.abc.domain.account.Account;
import com.abc.domain.sub.money.Money;

public interface Interest {
    
    Money amount(Account account);
}
