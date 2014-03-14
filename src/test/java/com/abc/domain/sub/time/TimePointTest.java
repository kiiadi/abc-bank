package com.abc.domain.sub.time;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class TimePointTest {

	@Test
	public void testAtMidnights() throws Exception {
		assertThat(TimePoint.atMidnight(2014, 3, 12), is(TimePoint.at(2014, 3, 12, 0, 0)));
	}
	
	@Test
    public void testIsBefore() throws Exception {
        TimePoint at1600 = TimePoint.at(2014, 3, 12, 16, 0);
        TimePoint at1630 = TimePoint.at(2014, 3, 12, 16, 30);
        
        assertThat(at1600.isBefore(at1600), is(false));
        assertThat(at1600.isBefore(at1630), is(true));
    }
    
    @Test
    public void testIsAfter() throws Exception { 
        TimePoint at1600 = TimePoint.at(2014, 3, 12, 16, 0);
        TimePoint at1630 = TimePoint.at(2014, 3, 12, 16, 30);
        
        assertThat(at1600.isAfter(at1600), is(false));
        assertThat(at1630.isAfter(at1600), is(true));
    }
    
    @Test
    public void testEquals() throws Exception { 
        TimePoint one = TimePoint.at(2014, 3, 12, 16, 0);
        TimePoint another = TimePoint.at(2014, 3, 12, 16, 0);
        
        assertThat(one, is(another));
    }
    
    @Test
    public void testAsDayOfYear() throws Exception { 
        assertThat(TimePoint.at(2014, 3, 12, 16, 30).asDayOfYear(), is(DayOfYear.at(2014, 3, 12)));
    }
    
    @Test
    public void testAsTimeOfDay() throws Exception { 
        assertThat(TimePoint.at(2014, 3, 12, 16, 30).asTimeOfDay(), is(TimeOfDay.at(16, 30)));
    }
}
