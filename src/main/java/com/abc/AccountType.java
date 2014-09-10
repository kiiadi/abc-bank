package com.abc;

public enum AccountType {
	CHECKING {

		@Override
		public String toString() {
			// TODO Auto-generated method stub
			return "Checking Account" ;
		}
		
	}, SAVINGS {
		@Override
		public String toString() {
			// TODO Auto-generated method stub
			return "Savings Account" ;
		}
	}, MAXI_SAVINGS {
		@Override
		public String toString() {
			// TODO Auto-generated method stub
			return "Maxi_Savings Account" ;
		}
	}, SUPER_SAVINGS {
		@Override
		public String toString() {
			// TODO Auto-generated method stub
			return "Super_Savings Account" ;
		};
}
}
