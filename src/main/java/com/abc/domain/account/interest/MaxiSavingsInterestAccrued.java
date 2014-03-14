package com.abc.domain.account.interest;

import java.util.List;

import com.abc.domain.account.Entry;
import com.abc.domain.sub.money.Money;
import com.abc.domain.sub.money.Ratio;
import com.abc.domain.sub.time.DayOfYear;

public class MaxiSavingsInterestAccrued extends AbstractInterestAccrued {
    
    private Ratio fiveHundredth = Ratio.of(0.05);
    private Ratio oneThousandth = Ratio.of(0.001);
    
    /**
     * TODO: accrued calculation logic seems wrong. need to clarify.
     */
    @Override
    public Money amount(List<Entry> entries) {
        Money totalInterest = Money.dollars(0);

        for (Entry each : entries) {
            totalInterest = totalInterest.plus(accruedAmount(each));
        }
        
        if (noWithdrawalFor10Days(entries)) {
            return totalInterest.of(fiveHundredth);
        }
        return totalInterest.of(oneThousandth);
    }
    
    boolean noWithdrawalFor10Days(List<Entry> entries) {
        DayOfYear tenDaysAgo = clock.now().asDayOfYear().rollDay(-10);
        DayOfYear lastWithdrawed = whenLastWithdrawed(entries);
        
        return lastWithdrawed == null || lastWithdrawed.isBefore(tenDaysAgo);
    }
    
    DayOfYear whenLastWithdrawed(List<Entry> entries) {
        DayOfYear lastWithdrawed = null;
        for (Entry entry : entries) {
            if (entry.amount().isLessThan(Money.dollars(0))) {
                lastWithdrawed = entry.whenBooked().asDayOfYear();
            }
        }
        return lastWithdrawed;
    }
}
