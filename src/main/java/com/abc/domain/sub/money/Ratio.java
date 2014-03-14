package com.abc.domain.sub.money;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Ratio {

	private static final RoundingMode DEFAULT_ROUNING_MODE = RoundingMode.HALF_EVEN;
	
	private BigDecimal numerator;
	private BigDecimal denominator;

	public static Ratio of(double ratio) {
		String ratioString = new Double(ratio).toString();
		int scale = ratioString.length() - ratioString.lastIndexOf(".") - 1;
		BigDecimal coversionRatio = new BigDecimal(ratio).setScale(scale, DEFAULT_ROUNING_MODE);
		return of(new BigDecimal(coversionRatio.unscaledValue()), new BigDecimal(Math.pow(10, scale)));
	}

	public static Ratio of(long numerator, long denominator) {
		return new Ratio(new BigDecimal(numerator), new BigDecimal(denominator));
	}

	public static Ratio of(BigDecimal numerator, BigDecimal denominator) {
		return new Ratio(numerator, denominator);
	}

	Ratio(BigDecimal numerator, BigDecimal denominator) {
		this.numerator = numerator;
		this.denominator = denominator;
	}

	BigDecimal getNumerator() {
		return numerator;
	}

	BigDecimal getDenominator() {
		return denominator;
	}

	public Ratio times(BigDecimal multiplier) {
		return Ratio.of(numerator.multiply(multiplier), denominator);
	}

	public Ratio times(Ratio multiplier) {
		return Ratio.of(numerator.multiply(multiplier.numerator), denominator.multiply(multiplier.denominator));
	}

	public BigDecimal decimalValue(int scale, RoundingMode roundingMode) {
		return numerator.divide(denominator, scale, roundingMode);
	}

	public Ratio reverse() {
		return Ratio.of(denominator, numerator);
	}
	
	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof Ratio)) {
			return false;
		}

		Ratio other = (Ratio) object;
		return numerator.equals(other.numerator) && denominator.equals(other.denominator);
	}

	@Override
	public int hashCode() {
		int result = 17;
		result = 37 * result + numerator.hashCode();
		result = 37 * result + denominator.hashCode();

		return result;
	}

	@Override
	public String toString() {
		return numerator + "/" + denominator;
	}
}
