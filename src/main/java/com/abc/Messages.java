package com.abc;

public enum Messages {
	   REQUEST_CUSTOMER_NAMES("Please enter first and last names:"),
	   REQUEST_ACCOUNT_NUMBER("Please enter account number:"),
	   REQUEST_CUSTOMER_ID("Please enter customer id:"),
	   REQUEST_DEPOSIT_AMOUNT("Please enter deposit amount in pounds:"),
	   REQUEST_WITHDRAWAL_AMOUNT("Please enter withdrawal amount:"),
	   REQUEST_NEW_OVERDRAFT_AMOUNT("Please enter the amount you would like the overdraft to be:"),
	   REQUEST_ORIGIN_ACCOUNT("Enter account Number to transfer money FROM:"),
	   REQUEST_DESTINATION_ACCOUNT("Enter account Number to transfer money TO:"),
	   REQUEST_TRANSFER_AMOUNT("Please enter amount to be transfered:"),
	   ERROR_GENERAL("An error has occured. Please try again"),
	   ERROR_INSUFFICIENT_FUNDS("There are insufficient funds to make this transfer"),
	   ERROR_ABOVE_WITHDRAWAL_LIMIT("The maximum daily withdrawal limit is £"),
	   ERROR_ACCOUNT_NOT_FOUND("A account matching the number provided could not be found"),
	   ERROR_WITHDRAWAL_AMOUNT_NEGATIVE("Withdrawal amount should not be a negative number"),
	   ERROR_DEST_ACC_NOT_FOUND("The destination account could not be found"),
	   ERROR_CUSTOMER_NOT_FOUND("No customer exists of that name"),
	   CONFIRMATION_TRANSFER("Transfer complete"),
	   CONFIRMATION_WITHDRAWAL("Withdrawal complete"),
	   CONFIRMATION_COMPLETE("Completed Successfully"),
	   CONFIRMATION_ACCOUNT_OPENED("A new account has been opended"),
	   CONFIRMATION_CUSTOMER_EXISING("Existing customer record found"),
	   CONFIRMATION_CUSTOMER_CREATED("New customer record recreated"),
	   TRANSACTION_HISTORY("Past Transactions"),
	   SESSION_END("Session has been ended");
	    
	    private final String text;

	    private Messages(String text) {
	        this.text = text;
	    }

	    /**
	     * Overridden to return the text value of the enum.
	     *
	     * @return text string
	     */
	    @Override
	    public String toString() {
	        return text;
	    }

	}
