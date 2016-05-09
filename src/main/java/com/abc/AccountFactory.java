package com.abc;


public class AccountFactory {
    /**
     * Getter method that returns an instance of the account object based on the account type specified
     * @param accountType the integer value of the type of account
     * @return the account object
     * @throws IllegalArgumentException if an invalid account type is specified
     */

    public static Account getAccount(int accountType) {
        switch(accountType) {
            case Account.CHECKING:
                return new CheckingAccount(Account.CHECKING);
            case Account.SAVINGS:
                return new SavingsAccount(Account.SAVINGS);
            case Account.MAXI_SAVINGS:
                return new MaxiSavingsAccount(Account.MAXI_SAVINGS);
            default:
                throw new IllegalArgumentException("Cannot create Account. Invalid Account Type");
        }
    }
}
