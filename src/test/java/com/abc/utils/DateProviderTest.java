package com.abc.utils;

import static org.junit.Assert.assertTrue;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.junit.Test;

/**
 * Test-cases follow the following method-name pattern:
 * methodName_Scenario_ExpectedResult()
 *
 */
public class DateProviderTest {

	@Test
	public void getInstance_WheneverItIsCalled_SameInstanceIsReturned() {
		DateProvider dateProvider1 = DateProvider.getInstance();
		DateProvider dateProvider2 = DateProvider.getInstance();
		
		assertTrue(dateProvider1 == dateProvider2);
	}

	@Test
	public void now_WhenCalled_TodaysDateIsReturned() {
		// get string date-format to compare only the "date" values 
		SimpleDateFormat dateFormat = new SimpleDateFormat("MMM dd yyyy");
		
		String strDate1 = dateFormat.format(Calendar.getInstance().getTime());
		String strDate2 = dateFormat.format(DateProvider.getInstance().now());
		
		assertTrue(strDate1.equals(strDate2));
	}
	
	@Test
	public void getDate_WhenPositiveNumberOfDaysIsPassed_FutureDateIsReturned() {
		// get string date-format to compare only the "date" values 
		SimpleDateFormat dateFormat = new SimpleDateFormat("MMM dd yyyy");
		
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DAY_OF_MONTH, 5);
		String strDate1 = dateFormat.format(calendar.getTime());
		
		String strDate2 = dateFormat.format(DateProvider.getInstance().getDate(5));
		
		assertTrue(strDate1.equals(strDate2));
	}
	
	@Test
	public void getDate_WhenNegativeNumberOfDaysIsPassed_PastDateIsReturned() {
		// get string date-format to compare only the "date" values 
		SimpleDateFormat dateFormat = new SimpleDateFormat("MMM dd yyyy");
		
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DAY_OF_MONTH, -10);
		String strDate1 = dateFormat.format(calendar.getTime());
		
		String strDate2 = dateFormat.format(DateProvider.getInstance().getDate(-10));
		
		assertTrue(strDate1.equals(strDate2));
	}
}
