/**
 * 
 */
package com.abc;

import static java.lang.Math.abs;
import java.text.DecimalFormat;
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
		 * @param _d the number to format
		 * @return a string representing the formatted number
		 */
		public static String toDollars(double _d)
			{
				return String.format("$%,.2f", abs(_d));
			}

		/**
		 * format a double to a string
		 * 
		 * @param _d the number to format
		 * @param _fmt the format of the string to use
		 * @return a string representing the formatted number
		 */
		public static String toDollars(double _d, String _fmt)
			{
				return String.format(_fmt, abs(_d));
			}

		/**
		 * Make sure correct plural of word is created based on the number
		 * passed in.
		 * 
		 * @param _number the number to analyze
		 * @param _word the word to add a plural to..oy!
		 * @return a string representing the number with an 's' at the end if it
		 *         is greater than zero.
		 */
		public static String makePlural(int _number, String _word)
			{
				// If the number passed in is 1 just return the word otherwise
				// add an 's' at the end
				return _number + " " + (_number == 1 ? _word : _word + "s");
			}

		/**
		 * Get todays date
		 * 
		 * @return return todays date
		 */
		public static Date now()
			{
				return Calendar.getInstance().getTime();
			}

		/**
		 * Simple periodic rate calculator. This function uses the decimal
		 * formatter to round to eitht places.
		 * 
		 * @param _annual_rate the annual base rate
		 * @param _num_periods the number of periods
		 * 
		 * @return the daily periodic rate
		 */
		public static double getPeriodicRate(double _annual_rate, int _num_periods)
			{
				double periodic_rate = 0.00;
				// use defimal formatter to round to eight places
				DecimalFormat df = new DecimalFormat("#.########");

				String s = df.format(_annual_rate / _num_periods);

				periodic_rate = Double.valueOf(s);

				return periodic_rate;
			}

		/**
		 * Simple future value calculator. Used to apply interest to the
		 * account.
		 * 
		 * @param _present_val present value of the account ( i.e. balance )
		 * @param _rate the interest rate
		 * @param _num_periods number of periods to calculate
		 * 
		 * @return the future value based on compound interest
		 */
		public static double futureValue(double _present_val, double _rate, int _num_periods)
			{
				Double fv = 0.00;
				// use defimal formatter to round to eight places
				DecimalFormat df = new DecimalFormat("#########.####");

				String s = df.format(_present_val * Math.pow(1 + _rate, _num_periods) );

				// convert back to double
				fv = Double.valueOf(s);
				
				return (fv);
			}
	}
