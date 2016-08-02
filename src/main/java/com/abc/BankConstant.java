package com.abc;

/**
 * Created by ashishsharma on 8/2/16.
 */
public enum BankConstant {

    AMOUNT_EXCEPTION("amount must be greater than zero"),
    ACCOUNT("account"),
    FIRST_CUSTOMER_EXCEPTION("First CustomerException"),
    CUSTOMARY_SUMMARY("Customer Summary"),
    NO_CUSTOMER("No customer found"),
    TOTAL_IN("Total In All Accounts"),
    STATEMENT("Statement for"),
    TOTAL("Total"),
    CHECKING_ACCOUNT("Checking Account"),
    SAVINGS_ACCOUNT("Savings Account"),
    MAXI_SAVINGS_ACCOUNT ("Maxi Savings Account"),
    WITHDRAWAL("withdrawal"),
    DEPOSIT("deposit");

    private String type;

    BankConstant(String type) {
        this.type = type;
    }

    public String type() {
        return type;
    }

}
