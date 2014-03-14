package com.abc.domain.account.interest;

import com.abc.domain.sub.money.Ratio;
import com.abc.domain.sub.time.DayOfYear;

public interface Accrued {
    
    Ratio rate(DayOfYear base, DayOfYear today);
}
