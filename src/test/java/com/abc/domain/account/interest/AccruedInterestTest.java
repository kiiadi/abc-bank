package com.abc.domain.account.interest;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.abc.domain.account.Account;
import com.abc.domain.account.Entry;
import com.abc.domain.sub.money.Money;
import com.abc.domain.sub.time.Clock;
import com.abc.domain.sub.time.DayOfYear;
import com.abc.domain.sub.time.TimePoint;

public class AccruedInterestTest {
    
    Clock clock;
    
    @Before
    public void setUp() throws Exception {
        this.clock = new Clock()
        {
            @Override
            public TimePoint now()
            {
                return TimePoint.at(2014, 3, 12, 22, 00);
            }
        };
    }
    
    /**
     * $18.25 * (10 / 365) =  $0.5
     */
    @Test
    public void testAccuredAmountOneEntry()
    {
        DayOfYear tenDaysAgo = DayOfYear.at(2014, 3, 2);
        
        List<Entry> entries = new ArrayList<Entry>();
        entries.add(new Entry(Money.dollars(18.25), tenDaysAgo.asTimePoint())); 
        
        Account mockAccount = mock(Account.class);
        when(mockAccount.entries()).thenReturn(entries);
        
        AccruedInterest accrued = new AccruedInterest(clock);
        assertThat(accrued.amount(mockAccount), is(Money.dollars(0.5)));
    }
    
    /**
     * $18.25 * (10 / 365) =  $0.5
     * $3,650 * (30 / 365) =  $300
     */
    @Test
    public void testAccuredAmountTwoEntries()
    {
        DayOfYear thirtyDaysAgo = DayOfYear.at(2014, 2, 10);
        DayOfYear tenDaysAgo = DayOfYear.at(2014, 3, 2);
        
        List<Entry> entries = new ArrayList<Entry>();
        entries.add(new Entry(Money.dollars(18.25), tenDaysAgo.asTimePoint()));  
        entries.add(new Entry(Money.dollars(3650), thirtyDaysAgo.asTimePoint()));
        
        Account mockAccount = mock(Account.class);
        when(mockAccount.entries()).thenReturn(entries);
        
        AccruedInterest accrued = new AccruedInterest(clock);
        assertThat(accrued.amount(mockAccount), is(Money.dollars(300.5)));
    }
    
    @Test
    public void testDayElasped()
    {
        DayOfYear today = DayOfYear.at(2014, 3, 12);
        
        DayOfYear tenDaysAgo = DayOfYear.at(2014, 3, 2);
        assertThat(new AccruedInterest(clock).dayElapsed(tenDaysAgo, today), is(10));
        
        DayOfYear thirtyDaysAgo = DayOfYear.at(2014, 2, 10);
        assertThat(new AccruedInterest(clock).dayElapsed(thirtyDaysAgo, today), is(30));
    }
}
