package com.abc;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Calendar;
import java.util.Date;

import org.junit.Test;

public class DateProviderTest {
    @Test
    public void singleton() {
    	DateProvider dp1 = DateProvider.getInstance();
    	DateProvider dp2 = DateProvider.getInstance();
    	assertEquals("Does not return singleton.", dp1, dp2);
    }

    @Test
    public void properNow() {
        Date dpTime = DateProvider.getInstance().now();
        assertTrue("Time is wrong in created object.", Calendar.getInstance().getTime().getTime() - dpTime.getTime() < 100);
    }
}
