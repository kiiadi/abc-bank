package com.abc.exception;

/**
 * 
 * @author Sanju Thomas
 *
 */
public class NotEnoughBalanceException extends ValidationException{

	private static final long serialVersionUID = 1L;
	
	public NotEnoughBalanceException(final double availableBalance, final double moneyRequested){
		super(constructMessage(availableBalance, moneyRequested));
	}
	
	private static String constructMessage(final double availableBalance, final double moneyRequested){
		final StringBuilder messageBuilder = new StringBuilder();
		messageBuilder.append("Unfortunately account doesn't have enough balance to serve the requested withdrwal! ");
		messageBuilder.append("Available balance: ");
		messageBuilder.append(availableBalance);
		messageBuilder.append("Requested amount: ");
		messageBuilder.append(moneyRequested);
		return messageBuilder.toString();
	}

}
