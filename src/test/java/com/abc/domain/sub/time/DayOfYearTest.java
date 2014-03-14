package com.abc.domain.sub.time;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class DayOfYearTest {

	@Test
    public void testGet() throws Exception {
        DayOfYear at20141224 = DayOfYear.at(2014, 12, 24);
        
        assertThat(at20141224.getYear(), is(2014));
        assertThat(at20141224.getMonth(), is(12));
        assertThat(at20141224.getDay(), is(24));
    }    

	@Test
	public void testEquals() throws Exception {
		DayOfYear one = DayOfYear.at(2014, 12, 24);
		DayOfYear another = DayOfYear.at(2014, 12, 24);
		
		assertThat(one, is(another));
	}
	
    @Test
    public void testIsBefore() throws Exception {
        assertThat(DayOfYear.at(2013, 12, 24).isBefore(DayOfYear.at(2014, 12, 24)), is(true));
        assertThat(DayOfYear.at(2014, 12, 24).isBefore(DayOfYear.at(2013, 12, 24)), is(false));
        
        assertThat(DayOfYear.at(2014, 11, 24).isBefore(DayOfYear.at(2014, 12, 24)), is(true));
        assertThat(DayOfYear.at(2014, 12, 24).isBefore(DayOfYear.at(2014, 11, 24)), is(false));
        
        assertThat(DayOfYear.at(2014, 11, 23).isBefore(DayOfYear.at(2014, 11, 24)), is(true));
        assertThat(DayOfYear.at(2014, 11, 24).isBefore(DayOfYear.at(2014, 11, 23)), is(false));
    }    

    @Test
    public void testIsAfter() throws Exception { 
        assertThat(DayOfYear.at(2014, 12, 25).isAfter(DayOfYear.at(2014, 12, 24)), is(true));
    }
    
    @Test
    public void testComapreTo() throws Exception {
        assertThat(DayOfYear.at(2014, 5, 19).compareTo(DayOfYear.at(2014, 5, 20)), is(-1));
        assertThat(DayOfYear.at(2014, 5, 19).compareTo(DayOfYear.at(2014, 5, 19)), is(0));
        assertThat(DayOfYear.at(2014, 5, 19).compareTo(DayOfYear.at(2014, 5, 18)), is(1));
    }
    
    @Test
	public void testRollDay() throws Exception {
		assertThat(DayOfYear.at(2014, 5, 19).rollDay(10), is(DayOfYear.at(2014, 5, 29)));
		assertThat(DayOfYear.at(2014, 5, 19).rollDay(20), is(DayOfYear.at(2014, 6, 8)));
		assertThat(DayOfYear.at(2014, 5, 19).rollDay(-20), is(DayOfYear.at(2014, 4, 29)));
	}
}
