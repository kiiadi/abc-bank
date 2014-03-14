package com.abc.domain.account.interest;

import com.abc.domain.account.Account;
import com.abc.domain.sub.money.Money;
import com.abc.domain.sub.money.Ratio;

public class AnnualInterest implements Interest {
    
    Ratio zeroPoint1Percent = Ratio.of(0.001);
    
    @Override
    public Money amount(Account account)
    {
        return account.balance().times(0.001);
    }
}
