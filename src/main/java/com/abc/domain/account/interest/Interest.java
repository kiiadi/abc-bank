package com.abc.domain.account.interest;

import java.util.List;

import com.abc.domain.account.Entry;
import com.abc.domain.sub.money.Money;

public interface Interest {
    
    Money amount(List<Entry> entries);
}
