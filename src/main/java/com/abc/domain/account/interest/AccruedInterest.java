package com.abc.domain.account.interest;

import com.abc.domain.account.Account;
import com.abc.domain.account.Entry;
import com.abc.domain.sub.money.Money;
import com.abc.domain.sub.money.Ratio;
import com.abc.domain.sub.time.Clock;
import com.abc.domain.sub.time.DayOfYear;
import com.abc.domain.sub.time.Duration;

public class AccruedInterest implements Interest {
    
    private static final int DAYS_PER_YEAR = 365;
    
    private DayOfYear today;
    
    public AccruedInterest(Clock clock) {
        today = clock.now().asDayOfYear();
    }
    
    @Override
    public Money amount(Account account)
    {
        Money totalInterest = Money.dollars(0);
        for (Entry each : account.entries()) {

            Ratio accruedRate = Ratio.of(dayElapsed(each.whenBooked().asDayOfYear(), today), DAYS_PER_YEAR);
            
            totalInterest = totalInterest.plus(each.amount().of(accruedRate));
        }
        
        return totalInterest;
    }
    
    int dayElapsed(DayOfYear from, DayOfYear to) {
        return Duration.between(from, to).asDays();
    }
}
