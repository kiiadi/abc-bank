package com.abc;

import java.util.Calendar;
import java.util.Date;

//I assume that the intention of this singleton class is to have a single point access
// to retrieve all date related services. Ex. if required, instead of getting current date from the
//Calendar (as doing below), you can get it from a configuration file or any other persistence. So,
// I continued to use it further. Also if required, new method(s) can be added to return any specific date needs.

public class DateProvider {

	private static final int MAXI_SAVINGS_REBATE_WITHDRAWL_DAYS_LIMIT=10;
	
	private static DateProvider instance = null;
	
	private DateProvider() {}
	
	public static DateProvider getInstance() 
	{ 
       if (instance == null) 
           instance = new DateProvider(); 
       return instance; 
	} 
   
	public Date now() 
	{ 
	   return Calendar.getInstance().getTime(); 
	}
	
	public Date rebateEligibleDate()
	{
		Date transactionDate = now();
		return  new Date (transactionDate.getTime() - MAXI_SAVINGS_REBATE_WITHDRAWL_DAYS_LIMIT);
	}
}
