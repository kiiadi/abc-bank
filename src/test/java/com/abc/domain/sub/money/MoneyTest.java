package com.abc.domain.sub.money;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertThat;

import java.math.BigDecimal;
import java.util.Currency;

import org.junit.Test;

public class MoneyTest {

	@Test
    public void testDollars() throws Exception {
        Money dollars100 = new Money(new BigDecimal(100), Currency.getInstance("USD"));
        assertThat(Money.dollars(100), is(dollars100));
    }
    
	@Test
    public void testFractionalDigits_USD() throws Exception {
        assertThat(Money.dollars(2.5012), is(Money.dollars(2.50)));
    }
	
	@Test
    public void testNegate() throws Exception {
        assertThat(Money.dollars(100).negate(), is(Money.dollars(-100)));
        assertThat(Money.dollars(-100).negate(), is(Money.dollars(100)));
    }

	@Test
    public void testPlus() throws Exception {
        assertThat(Money.dollars(250).plus(Money.dollars(150)), is(Money.dollars(400)));
        assertThat(Money.dollars(-250).plus(Money.dollars(350)), is(Money.dollars(100)));
    }
    
	@Test
    public void testMinus() throws Exception {
        assertThat(Money.dollars(250).minus(Money.dollars(150)), is(Money.dollars(100)));
        assertThat(Money.dollars(250).minus(Money.dollars(350)), is(Money.dollars(-100)));
        assertThat(Money.dollars(-250).minus(Money.dollars(150)), is(Money.dollars(-400)));
        assertThat(Money.dollars(250).minus(Money.dollars(-150)), is(Money.dollars(400)));
    }

    @Test
    public void testTimes() throws Exception {
        assertThat(Money.dollars(30.15).times(3.26), is(Money.dollars(98.29)));
        assertThat(Money.dollars(125.05).times(2.15), is(Money.dollars(268.86)));
    }

    @Test
    public void testRatio() throws Exception {
        assertThat(Money.dollars(100.54).of(Ratio.of(0.5)), is(Money.dollars(50.27)));
        assertThat(Money.dollars(100.54).of(Ratio.of(1, 2)), is(Money.dollars(50.27)));
        assertThat(Money.dollars(18.25).of(Ratio.of(10, 365)), is(Money.dollars(0.5)));
        assertThat(Money.dollars(10).of(Ratio.of(1, 365)), is(Money.dollars(0.03)));

        assertThat(Money.dollars(246.57).of(Ratio.of(0.001)), is(Money.dollars(0.25)));
        assertThat(Money.dollars(27.39).of(Ratio.of(0.001)), is(Money.dollars(0.03)));
    }
    
	@Test
    public void testIsGreaterThan() throws Exception {
		assertThat(Money.dollars(100).isGreaterThan(Money.dollars(99.99)), is(true));
		assertThat(Money.dollars(100).isGreaterThan(Money.dollars(100)), is(false));
    }

	@Test
    public void testIsLessThan() throws Exception {
		assertThat(Money.dollars(100).isLessThan(Money.dollars(100.01)), is(true));
		assertThat(Money.dollars(100).isLessThan(Money.dollars(100)), is(false));
    }

	@Test
    public void testEqual() throws Exception {
        assertThat(Money.dollars(100), is(Money.dollars(100)));
        assertThat(Money.dollars(100), is(Money.valueOf(new BigDecimal(100), Currency.getInstance("USD"))));
    }

	@Test
    public void testNotEqual() throws Exception {
		assertThat(Money.dollars(100), not(Money.dollars(200)));
		assertThat(Money.dollars(100), not(Money.valueOf(new BigDecimal(100), Currency.getInstance("EUR"))));
    }
    
	@Test
    public void testToString() throws Exception {
        assertThat(Money.dollars(100).toString(), is("$100.00"));
    }
}
