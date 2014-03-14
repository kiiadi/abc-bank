package com.abc.domain.account.interest;

import java.util.List;

import com.abc.domain.account.Entry;
import com.abc.domain.sub.money.Money;
import com.abc.domain.sub.money.Ratio;

public class CheckingInterestAccrued extends AbstractInterestAccrued {
    
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
        
        return totalInterest.of(oneThousandth);
    }
}
