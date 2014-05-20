package com.abc.api;

import com.abc.interfaces.BankingSystemFacade;
import com.abc.interfaces.GLReportingApi;
import com.abc.interfaces.GeneralLedgerApi;

public class BankingServices implements BankingSystemFacade{

	public GeneralLedgerApi getGeneralLedgerApi(String entityCode) {		 
		return new GeneralLedger(entityCode);
	}
	
	public GLReportingApi getGLReportingApi() {		 
		return new GLReporting();
	}

}
