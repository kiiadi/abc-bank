package com.abc;

import static java.lang.Math.abs;

public class Constants {
	public static final String NEGATIVE_AMOUNT_ERROR = "Amount must be greater than zero.";
	public static final String ZERO_AMOUNT_ERROR = "Transaction amount must not be zero.";
	public static final String NOT_ENOUGH_BALANCE_ERROR = "Withdrawal amount is greater that the account balance";
	public static final String FROM_ACCOUNT_ERROR = "Transfer denied as \"fromAccount\" does not exist";
	public static final String TO_ACCOUNT_ERROR = "Transfer denied as \"toAccount\" does not exist";
	public static final String DUPLICATE_ACCOUNT_ERROR = "Can not create this account as it already exists";
	public static final String FROM_TO_ACCOUNT_ERROR = "Transfer denied as \"fromAccount\" is the same as \"toAccount\"";
	public static final String DUPLICATE_CUSTOMER_ERROR = "Customer already exists";
	
	public static final double CHECKING_DAILY_INTEREST_RATE = 0.001d/365.0d;
	public static final double SAVING_DAILY_INTEREST_RATE_1 = 0.001d/365.0d;
	public static final double SAVING_DAILY_INTEREST_RATE_2 = 0.002d/365.0d;
	public static final double MAXI_SAVING_DAILY_INTEREST_RATE_1 = 0.02d/365.0d;
	public static final double MAXI_SAVING_DAILY_INTEREST_RATE_2 = 0.05d/365.0d;
	public static final double MAXI_SAVING_DAILY_INTEREST_RATE_3 = 0.10d/365.0d;

    public static String toDollars(double d){
        return String.format("$%,.2f", abs(d));
    }

}
