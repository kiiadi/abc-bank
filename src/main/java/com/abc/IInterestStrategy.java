package com.abc;

public interface IInterestStrategy {
	public static double POINT_ONE_PERCENT = 0.001;
	public static double POINT_TWO_PERCENT = 0.002;

	public static double TWO_PERCENT = 0.02;
	public static double FIVE_PERCENT = 0.05;
	public static double TEN_PERCENT = 0.1;
	public static double ONE_THOUSAND = 1000;
	public static double TWO_THOUSAND = 2000;
	public static double TWENTY = 20;
	public static double SEVENTY = 70;

	public double calculateInterest(double amount);

}
