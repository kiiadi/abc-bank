package com.abc.domain.account.interest;

import com.abc.domain.sub.money.Ratio;
import com.abc.domain.sub.time.DayOfYear;
import com.abc.domain.sub.time.Duration;

public class CalendarDayAccrued implements Accrued {
    
    private static final int DAYS_PER_YEAR = 365;
    
    @Override
    public Ratio rate(DayOfYear base, DayOfYear today) {
        return Ratio.of(dayElapsed(base, today), DAYS_PER_YEAR);
    }
    
    protected int dayElapsed(DayOfYear from, DayOfYear to) {
        return Duration.between(from, to).asDays();
    }
}
