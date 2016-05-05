package com.abc;


public class AccountFactory {

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
