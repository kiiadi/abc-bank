package com.abc.bank.account;

/**
 * Returns an account implementation
 * base don the requested Account type
 *
 */
public class AccountFactory {
    
    public static Account newAccount(AccountType type){
        Account account = null;
        switch(type){
        case savings:
            account = new SavingsAccount();
            break;
        case checking:
            account = new CheckingAccount();
            break;
        case maxiSavings:
            account = new MaxiSavingsAccount();
            break;
        }
        return account;
    }

}
