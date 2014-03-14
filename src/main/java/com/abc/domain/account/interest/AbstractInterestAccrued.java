package com.abc.domain.account.interest;

import java.util.List;

import com.abc.domain.account.Entry;
import com.abc.domain.sub.money.Money;
import com.abc.domain.sub.money.Ratio;
import com.abc.domain.sub.time.Clock;
import com.abc.domain.sub.time.DayOfYear;
import com.abc.domain.sub.time.WorldClock;

public abstract class AbstractInterestAccrued implements Interest {
    
    protected Accrued accruedRate;
    protected Clock clock;
    
    public AbstractInterestAccrued() {
        clock = new WorldClock();
        accruedRate = new CalendarDayAccrued();
    }
    
    protected Ratio daysAccruedRate(DayOfYear whenBooked) {
        return accruedRate.rate(whenBooked, clock.now().asDayOfYear());
    }
    
    protected Money accruedAmount(Entry entry) {
        return entry.amount().of(daysAccruedRate(entry.whenBooked().asDayOfYear()));
    }
    
    protected void setClock(Clock clock) {
        this.clock = clock;
    }

    @Override
    public abstract Money amount(List<Entry> entries);
}
