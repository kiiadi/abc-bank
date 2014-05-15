/**
 * 
 */
package com.abc;

import static java.lang.Math.abs;
import java.util.Calendar;
import java.util.Date;

/**
 * A class to encapsulate utilities across the application
 * 
 * @author Jeff
 * 
 */
public class Utils
	{

		/**
		 * format a double to a string
		 * 
		 * @param d - the number to format
		 * @return a string representing the formatted number
		 */
		public static String toDollars(double d)
			{
				return String.format("$%,.2f", abs(d));
			}

		/**
		 * format a double to a string
		 * 
		 * @param d - the number to format
		 * @return a string representing the formatted number
		 */
		public static String toDollars(double _d, String _fmt )
			{
				return String.format(_fmt, abs(_d));
			}

		/**
		 * Make sure correct plural of word is created based on the number
		 * passed in.
		 * 
		 * @param number - the number to analyze
		 * @param word - the word to add a plural to..oy!
		 * @returns - a string representing the number with an 's' at the end if
		 *          it is greater than zero.
		 */
		public static String makePlural(int number, String word)
			{
				// If the number passed in is 1 just return the word otherwise
				// add an 's' at the end
				return number + " " + (number == 1 ? word : word + "s");
			}

	    public static Date now() {
	        return Calendar.getInstance().getTime();
	    }		
	}
