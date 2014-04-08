package com.abc.interests;

public interface InterestStrategy {

	public static final double DAYS_IN_A_YEAR = 365.0;
	public static final double SAVING_THRESHOLD = 1000;
	public static final double MAX_SAVING_THRESHOLD = 2000;
	public static final double FLAT_RATE = 0.001;
	public static final double SAVING_RATE = 0.002;
	public static final double PROMOTED_SAVING_RATE = 0.005;
	public static final double MAX_SAVING_RATE = 0.1;

	
	public double calculateInterest(double balance, int days);

}