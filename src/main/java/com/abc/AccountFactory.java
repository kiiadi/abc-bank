package com.abc;

public class AccountFactory {

	public static Account newAccount(AccountType type) throws InvalidAccount  {
		Account newAcct ;
		switch ( type ) {
		case CHECKING :
			newAcct = new CheckingAccount() ;
			break ;
		case MAXI_SAVINGS:
			newAcct = new MaxiSavingsAccount() ;
			break;
		case SAVINGS:
			newAcct = new SavingsAccount() ;
			break;
		case SUPER_SAVINGS:
			newAcct = new SuperSavingsAccount() ;
			break;
		default:
			newAcct = null ;
			break;
		}
		if ( newAcct == null ) {
			throw new InvalidAccount("Invalid Account Type") ;
		}
		return newAcct ;
	}

}
