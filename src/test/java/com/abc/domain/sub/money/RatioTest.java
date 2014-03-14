package com.abc.domain.sub.money;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.math.BigDecimal;
import java.math.RoundingMode;

import org.junit.Test;

public class RatioTest {

	@Test
	public void testTimesByOne() throws Exception {
		assertThat(Ratio.of(1270, 5).times(new BigDecimal(1)), is(Ratio.of(1270, 5)));
	}

	@Test
	public void testTimesByTwo() throws Exception {
		assertThat(Ratio.of(1270, 5).times(new BigDecimal(2)), is(Ratio.of(1270*2, 5)));
	}

	@Test
	public void testDoubleRatio() throws Exception {
		Ratio ratio = Ratio.of(1270.52);
		assertThat(ratio.getNumerator(), is(new BigDecimal(127052)));
		assertThat(ratio.getDenominator(), is(new BigDecimal(100)));

		ratio = Ratio.of(1270.5);
		assertThat(ratio.getNumerator(), is(new BigDecimal(12705)));
		assertThat(ratio.getDenominator(), is(new BigDecimal(10)));
	}

	@Test
	public void testTimesByRatio() throws Exception {
		Ratio result = Ratio.of(100, 10).times(Ratio.of(20, 10));
		assertThat(result.getNumerator(), is(new BigDecimal(2000)));
		assertThat(result.getDenominator(), is(new BigDecimal(100)));
	}

	@Test
	public void testDecimalValue() throws Exception {
		Ratio ratio = Ratio.of(120, 10);
		assertThat(ratio.decimalValue(0, RoundingMode.HALF_EVEN), is(new BigDecimal(12)));
		assertThat(ratio.times(new BigDecimal(2)).decimalValue(0, RoundingMode.HALF_EVEN), is(new BigDecimal(24)));

		ratio = Ratio.of(100, 3);
		assertThat(ratio.decimalValue(0, RoundingMode.HALF_EVEN), is(new BigDecimal(33)));
		assertThat(ratio.times(new BigDecimal(2)).decimalValue(0, RoundingMode.DOWN), is(new BigDecimal(66)));
		assertThat(ratio.times(new BigDecimal(3.1)).decimalValue(1, RoundingMode.DOWN), is(new BigDecimal(103.3).setScale(1, RoundingMode.HALF_EVEN)));
	}
	
    @Test(expected=ArithmeticException.class)
	public void testZero() throws Exception {
		Ratio ratio = Ratio.of(10, 0);
		ratio.decimalValue(2, RoundingMode.HALF_UP);
	}
}
