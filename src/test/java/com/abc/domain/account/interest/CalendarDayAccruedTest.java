package com.abc.domain.account.interest;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

import com.abc.domain.sub.money.Ratio;
import com.abc.domain.sub.time.DayOfYear;

public class CalendarDayAccruedTest {
    
    @Test
    public void testDayElapsed() throws Exception {
        DayOfYear twelveDaysAgo = DayOfYear.at(2014, 3, 1);
        DayOfYear thirtyDaysAgo = DayOfYear.at(2014, 2, 11);
        
        assertThat(new CalendarDayAccrued().dayElapsed(thirtyDaysAgo, twelveDaysAgo), is(18));
    }
    
    @Test
    public void testRate() throws Exception {
        DayOfYear twelveDaysAgo = DayOfYear.at(2014, 3, 1);
        DayOfYear thirtyDaysAgo = DayOfYear.at(2014, 2, 11);
        
        assertThat(new CalendarDayAccrued().rate(thirtyDaysAgo, twelveDaysAgo), is(Ratio.of(18, 365)));
    }
}
