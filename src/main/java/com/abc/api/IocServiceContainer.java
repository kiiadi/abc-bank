package com.abc.api;

import com.abc.interfaces.BankSystemServices;
import com.abc.interfaces.GeneralLedgerApi;

public class IocServiceContainer implements BankSystemServices{

	public GeneralLedgerApi getGeneralLedgerApi(String entityCode) {		 
		return new GeneralLedger(entityCode);
	}

}
