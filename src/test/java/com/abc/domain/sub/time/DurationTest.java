package com.abc.domain.sub.time;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class DurationTest {
    
    @Test
    public void testEqual() throws Exception {
        Duration one = Duration.of(Duration.DAYS_IN_MILLESECONDS);
        Duration another = Duration.of(Duration.DAYS_IN_MILLESECONDS);
        
        assertThat(one, is(another));
    }
    
    @Test
    public void testNotEqual() throws Exception {
        Duration one = Duration.of(Duration.DAYS_IN_MILLESECONDS);
        Duration another = Duration.of(Duration.HOURS_IN_MILLESECONDS);
        
        assertThat(one, not(another));
    }
    
    @Test
    public void testBetweenSameYear() throws Exception
    {
        Duration tenDays = Duration.between(DayOfYear.at(2014, 3, 2), DayOfYear.at(2014, 3, 12));
        
        assertThat(tenDays, is(Duration.of(10 * Duration.DAYS_IN_MILLESECONDS)));
        assertThat(tenDays.asDays(), is(10));
    }
    
    @Test
    public void testBetweenDifferentYear() throws Exception
    {
        Duration oneYearAndTenDays = Duration.between(DayOfYear.at(2013, 3, 2), DayOfYear.at(2014, 3, 12));
        
        assertThat(oneYearAndTenDays, is(Duration.of(375 * Duration.DAYS_IN_MILLESECONDS)));
        assertThat(oneYearAndTenDays.asDays(), is(375));
    }
}
