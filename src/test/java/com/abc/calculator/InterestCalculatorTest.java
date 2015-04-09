package com.abc.calculator;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.junit.Test;

public class InterestCalculatorTest {

	@Test
	public void testCalculate() throws ParseException {
		double interest = InterestCalculator.calculate(10000.00d, 0.01d, 
				new SimpleDateFormat("yyyy/MM/dd").parse("2015/01/01"), 
				new SimpleDateFormat("yyyy/MM/dd").parse("2015/04/09"));
		
		assertEquals(new BigDecimal(interest).setScale(2, BigDecimal.ROUND_HALF_UP), 
				 new BigDecimal(0.27D).setScale(2, BigDecimal.ROUND_HALF_UP));
	}

}
