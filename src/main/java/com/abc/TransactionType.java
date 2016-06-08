package com.abc;

public enum TransactionType {
    DEPOSIT("Deposit"),
    VIEW_BALANCE("View Balance"),
    WITHDRAW("Withdraw"),
    TRANSFER("Funds Transfer");
    private final String text;

    private TransactionType(String text) {
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