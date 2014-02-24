package com.abc;

public class InterestStrategyImpl implements IInterestStrategy {
	private AccountType type;
	public InterestStrategyImpl(AccountType type){
		this.type=type;
	}

	public double calculateInterest(double amount) {
		IInterestStrategy strategy;
		
		switch (type) {
		case SAVINGS:
			strategy=new SavingsInterestStrategyImpl();
			break;
		case MaxiSavings:
			strategy=new MaxSavingsInterestStrategyImpl();
			break;
		default:
			strategy=new DefaultInterestStrategyImpl();
		}
		return strategy.calculateInterest(amount);
	}

}
