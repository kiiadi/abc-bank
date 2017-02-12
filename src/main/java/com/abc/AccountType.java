package com.abc;

public enum AccountType {
    CHECKING("Checking Account"),
    SAVINGS("Savings Account"),
    MAXI_SAVINGS("Maxi Savings Account");

    private final String readableForm;

    AccountType(String readableForm){
        this.readableForm = readableForm;
    }

    public String getReadableForm() {
        return readableForm;
    }
}
